package com.espartanhack.pokedex20.capturarpokemon

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.espartanhack.pokedex20.core.data.db.dao.PokemonDao
import com.espartanhack.pokedex20.core.data.db.dao.ZoneDao
import com.espartanhack.pokedex20.core.data.db.entities.EventsEntity
import com.espartanhack.pokedex20.core.data.db.relations.CapturedPokemonsCrossRef
import com.espartanhack.pokedex20.core.domain.classes.Prefs
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.dao.EventsDao
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.serialization.JsonConvertException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.json.JSONException
import javax.inject.Inject

@HiltViewModel
class CapturarPokemonViewModel @Inject constructor(
    prefs: Prefs,
    private val eventsDao: Lazy<EventsDao>,
    private val zoneDaoDb: ZoneDao,
    private val pokemonDao: Lazy<PokemonDao>
) : ViewModel() {

    val added: StateFlow<Boolean>
        field = MutableStateFlow(value = false)

    val failure: StateFlow<Failure?>
        field = MutableStateFlow(value = null)

    val teamId = prefs.getString(Prefs.TEAM_ID).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        initialValue = ""
    )

    fun getZones(teamId: String) = zoneDaoDb.getZones().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        initialValue = emptyList()
    )

    suspend fun onClickZona(zoneId: String) {
        Log.d("CapturarPokemonViewModel", "onClickZona: $zoneId")
        try {
            val event = eventsDao.get().postEvent(zoneId)
            if (event.catchedPokemonId != null) {
                pokemonDao.get().upsertCatchedPokemons(
                    event.pokemonsEvent.map { pokemon ->
                        CapturedPokemonsCrossRef(
                            id = event.catchedPokemonId,
                            teamId = event.teamId,
                            pokemonId = pokemon.pokemonId
                        )
                    }
                )
                added.value = true
            } else {
                added.value = false
                throw JsonConvertException("No pokemon catched")
            }
            zoneDaoDb.upsertEvent(
                EventsEntity(
                    teamId = event.teamId,
                    capturedPokemonId = event.catchedPokemonId
                )
            )
        } catch (e: JsonConvertException) {
            Log.d("CapturarPokemonViewModel", "Error: ${e.message}")
            if (e.message?.contains("You cannot generate a new event at this point of time") == true) {
                failure.value = Failure.COOLDOWN_NOT_FINISHED
            } else if (e.message?.contains("No pokemon catched") == true) {
                failure.value = Failure.NO_POKEMON_CATCHED
            } else {
                failure.value = Failure.OTHER_FAILURE
            }
        }
    }

    fun resetErrors() {
        failure.value = null
    }

    enum class Failure {
        NO_POKEMON_CATCHED,
        COOLDOWN_NOT_FINISHED,
        OTHER_FAILURE
    }
}