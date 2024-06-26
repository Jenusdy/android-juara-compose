package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()


    fun onDessertClicked() {
        _uiState.update { cupcakeUiState ->
            val dessertsSold = cupcakeUiState.dessertSold + 1
            val nextDesertIndex = determineDessertToShow(dessertsSold)
            cupcakeUiState.copy(
                currentDessertIndex = nextDesertIndex,
                revenue = cupcakeUiState.revenue + cupcakeUiState.currentDessertIndex,
                dessertSold = dessertsSold,
                currentDessertImageId = dessertList[nextDesertIndex].imageId,
                currentDessertPrice = dessertList[nextDesertIndex].price
            )
        }
    }

    private fun determineDessertToShow(
        dessertsSold: Int
    ): Int {
        var dessertIndex = 0

        for (index in dessertList.indices) {
            if (dessertsSold >= dessertList[index].startProductionAmount) {
                dessertIndex = index
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertIndex
    }
}