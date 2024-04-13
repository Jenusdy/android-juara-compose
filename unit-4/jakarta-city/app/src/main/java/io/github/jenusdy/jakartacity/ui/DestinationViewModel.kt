package io.github.jenusdy.jakartacity.ui

import androidx.lifecycle.ViewModel
import androidx.room.util.copy
import io.github.jenusdy.jakartacity.data.DestinationType
import io.github.jenusdy.jakartacity.data.local.DataSource
import io.github.jenusdy.jakartacity.data.local.Destination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class DestinationViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DestinationUiState())
    val uiState: StateFlow<DestinationUiState> = _uiState

    init {
        initializeUIState()
    }

    private fun initializeUIState() {
        val destinations: Map<DestinationType, List<Destination>> =
            DataSource.destinationList.groupBy { it.type }
        _uiState.value = DestinationUiState(
            destinations = destinations,
            currentSelectedDestination = destinations[DestinationType.Travel]?.get(0)
                ?: DataSource.defaultDestination
        )
    }

    fun resetHomeScreenStates() {
        _uiState.update {
            it.copy(
                currentSelectedDestination = it.destinations[it.currentDestination]?.get(0)
                    ?: DataSource.defaultDestination,
                isShowingHomepage = true
            )
        }
    }

    fun updateCurrentDestination(destinationType: DestinationType) {
        _uiState.update {
            it.copy(
                currentDestination = destinationType
            )
        }
    }

}