package com.example.dessertclicker.ui

import com.example.dessertclicker.R

data class DessertUiState(
    val revenue: Int = 0,
    val dessertSold: Int = 0,
    val currentDessertIndex: Int = 0,
    val currentDessertImageId: Int = R.drawable.cupcake,
    val currentDessertPrice: Int = 0
)
