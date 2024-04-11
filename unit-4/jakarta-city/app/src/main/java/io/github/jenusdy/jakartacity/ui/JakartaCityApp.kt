package io.github.jenusdy.jakartacity.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Museum
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.jenusdy.jakartacity.R
import io.github.jenusdy.jakartacity.data.DestinationType
import io.github.jenusdy.jakartacity.ui.theme.JakartaCityTheme

@Composable
fun JakartaCityApp(

) {
    val viewModel: DestinationViewModel = viewModel()
    val destinationUiState: DestinationUiState = viewModel.uiState.collectAsState().value

    val navigationItemContentList = listOf(
        NavigationItemContent(
            destinationType = DestinationType.Travel,
            icon = Icons.Filled.Museum,
            text = stringResource(R.string.travel_destination)
        ),
        NavigationItemContent(
            destinationType = DestinationType.Restaurant,
            icon = Icons.Filled.Restaurant,
            text = stringResource(R.string.restaurant)
        ),
        NavigationItemContent(
            destinationType = DestinationType.Library,
            icon = Icons.Filled.CollectionsBookmark,
            text = stringResource(R.string.library)
        ),
        NavigationItemContent(
            destinationType = DestinationType.Park,
            icon = Icons.Filled.Park,
            text = stringResource(R.string.park)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.inverseOnSurface)
    ) {
        BottomNavigationBar(
            currentTab = destinationUiState.currentDestination,
            onTabPressed = {
                viewModel.updateCurrentDestination(destination = it)
            },
            navigationItemContentList = navigationItemContentList
        )
    }
}


@Composable
private fun BottomNavigationBar(
    currentTab: DestinationType,
    onTabPressed: ((DestinationType) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentTab == navItem.destinationType,
                onClick = { onTabPressed(navItem.destinationType) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text
                    )
                }
            )
        }
    }
}

private data class NavigationItemContent(
    val destinationType: DestinationType,
    val icon: ImageVector,
    val text: String
)

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun JakartaCityAppCompactPreview() {
    JakartaCityTheme {
        Surface {
            JakartaCityApp()
        }
    }
}