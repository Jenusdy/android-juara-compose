package io.github.jenusdy.jakartacity.data.local

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import io.github.jenusdy.jakartacity.data.DestinationType

data class Destination(
    val id: Long,
    @StringRes val title: Int,
    @StringRes val description: Int,
    val rating: Double,
    @DrawableRes val image: Int,
    var type: DestinationType = DestinationType.Travel
)
