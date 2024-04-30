package io.github.jenusdy.mars_photos.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {

    var marsUiState: String by mutableStateOf("")
        private set

    init {
        getMarsPhotos()
    }

    fun getMarsPhotos() {
        marsUiState = "Set the Mars API Status Response Here"
    }

}