package io.github.jenusdy.mars.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jenusdy.mars.network.MarsApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

class MarsViewModel : ViewModel() {
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        viewModelScope.launch {
            try {
                val listResult = MarsApi.retrofitService.getPhotos()
                marsUiState = MarsUiState.Success(listResult)
            } catch (e: IOException) {
                marsUiState = MarsUiState.Error
            }
        }
    }
}