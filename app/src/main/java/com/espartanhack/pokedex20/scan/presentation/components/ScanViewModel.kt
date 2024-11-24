package com.espartanhack.pokedex20.scan.presentation.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.APITokens
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.entity.ZoneEntity
import com.espartanhack.pokedex20.core.domain.pokeHackAPI.httpClient
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.request
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor() : ViewModel() {

    val data: StateFlow<String?>
        field = MutableStateFlow(value = null)

    /**
     * Process the data extracted from the Qr code.
     *
     * @param data The data extracted from the Qr code.
     */
    fun processQrData(data: String) {
        viewModelScope.launch {
            httpClient.use {
                val zoneCode = data.split("/").last()
                val zone: ZoneEntity = it.get("${APITokens.API_URL}/zones/$zoneCode").body()
                this@ScanViewModel.data.value = zone.name
            }
        }
    }
}