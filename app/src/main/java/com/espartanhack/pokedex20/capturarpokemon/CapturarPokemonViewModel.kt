package com.espartanhack.pokedex20.capturarpokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.espartanhack.pokedex20.core.data.db.dao.ZoneDao
import com.espartanhack.pokedex20.core.domain.classes.Prefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CapturarPokemonViewModel @Inject constructor(
    prefs: Prefs,
    private val zoneDao: ZoneDao
) : ViewModel() {

    val teamId = prefs.getString(Prefs.TEAM_ID).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        initialValue = ""
    )

    fun getZones(teamId: String) = zoneDao.getZones(teamId).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        initialValue = emptyList()
    )
}