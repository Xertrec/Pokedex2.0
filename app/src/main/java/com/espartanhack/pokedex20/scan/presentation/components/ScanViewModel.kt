package com.espartanhack.pokedex20.scan.presentation.components

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
        this.data.value = data
    }
}