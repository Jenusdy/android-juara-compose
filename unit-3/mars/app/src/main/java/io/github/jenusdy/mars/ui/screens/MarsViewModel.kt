package io.github.jenusdy.mars.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jenusdy.mars.network.MarsApi
import kotlinx.coroutines.launch
import java.io.IOException

class MarsViewModel : ViewModel() {
    var marsUiState: String by mutableStateOf("")
        private set

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        viewModelScope.launch {
            try {
                val listResult = MarsApi.retrofitService.getPhotos()
                marsUiState = listResult
            } catch (e: IOException) {
                marsUiState = "No Internet Connection!"
            }
        }
    }
}