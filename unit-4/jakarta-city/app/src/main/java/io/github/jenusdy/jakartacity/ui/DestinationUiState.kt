package io.github.jenusdy.jakartacity.ui

import io.github.jenusdy.jakartacity.data.DestinationType
import io.github.jenusdy.jakartacity.data.local.DataSource
import io.github.jenusdy.jakartacity.data.local.Destination

data class DestinationUiState (
    val destinationType: Map<DestinationType, List<Destination>> = emptyMap(),
    val currentDestination: DestinationType = DestinationType.Travel,
    val isShowingHomepage: Boolean = true
)