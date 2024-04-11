package io.github.jenusdy.jakartacity.ui

import androidx.lifecycle.ViewModel
import androidx.room.util.copy
import io.github.jenusdy.jakartacity.data.DestinationType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class DestinationViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DestinationUiState())
    val uiState: StateFlow<DestinationUiState> = _uiState

    fun updateCurrentDestination(destination: DestinationType){
        _uiState.update {
            it.copy(
                currentDestination = destination
            )
        }
    }

    fun resetHomeScreenStates() {
        _uiState.update {
            it.copy(
                isShowingHomepage = true
            )
        }
    }
}