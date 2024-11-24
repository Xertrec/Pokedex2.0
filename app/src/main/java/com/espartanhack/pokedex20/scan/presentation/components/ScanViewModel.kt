package com.espartanhack.pokedex20.scan.presentation.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.espartanhack.pokedex20.core.data.db.dao.PokemonDao
import com.espartanhack.pokedex20.core.data.db.dao.ZoneDao
import com.espartanhack.pokedex20.core.data.db.entities.EventsEntity
import com.espartanhack.pokedex20.core.data.db.entities.ZonesEntity
import com.espartanhack.pokedex20.core.data.db.relations.CapturedPokemonsCrossRef
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.dao.EventsDao
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.dao.ZonesDao
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val pokemonDao: Lazy<PokemonDao>,
    private val zoneDaoDb: Lazy<ZoneDao>,
    private val zoneDao: Lazy<ZonesDao>,
    private val eventDao: Lazy<EventsDao>
) : ViewModel() {

    val zoneId: StateFlow<String?>
        field = MutableStateFlow(value = null)

    /**
     * Process the data extracted from the Qr code.
     *
     * @param data The data extracted from the Qr code.
     */
    fun processQrData(data: String) {
        if (data == "") return

        viewModelScope.launch {
            val zoneCode = data.split("/").last()
            val zone = zoneDao.get().getZone(zoneCode)
            zoneDaoDb.get().upsertZone(
                ZonesEntity(
                    id = zone.id,
                    name = zone.name
                )
            )
            val event = eventDao.get().postEvent(zone.id)
            zoneDaoDb.get().upsertEvent(
                EventsEntity(
                    teamId = event.teamId,
                    capturedPokemonId = event.catchedPokemonId
                )
            )
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
            }
            this@ScanViewModel.zoneId.value = zone.id
        }
    }
}