package com.espartanhack.pokedex20.pokemoncapturados

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.espartanhack.pokedex20.core.data.db.dao.PokemonDao
import com.espartanhack.pokedex20.core.domain.classes.Prefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PokemonCapturadosViewModel @Inject constructor(
    private val prefs: Prefs,
    private val pokemonDao: PokemonDao
) : ViewModel() {

    val teamId = prefs.getString(Prefs.TEAM_ID).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = null
    )

    fun pokemonCapturados(teamId: String) = pokemonDao.getCatchedPokemons(teamId).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
}