package io.github.jenusdy.jakartacity.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Museum
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.jenusdy.jakartacity.R
import io.github.jenusdy.jakartacity.data.DestinationType
import io.github.jenusdy.jakartacity.data.local.DataSource
import io.github.jenusdy.jakartacity.data.local.Destination
import io.github.jenusdy.jakartacity.ui.theme.JakartaCityTheme

@Composable
fun JakartaCityApp(
    modifier: Modifier = Modifier
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

    Box(modifier = modifier) {
        Row(modifier = modifier) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
            ) {
                DestinationContent(
                    uiState = destinationUiState,
                    onDestinationCardPressed = {},
                    modifier = Modifier.weight(1f)
                )
                BottomNavigationBar(
                    currentTab = destinationUiState.currentDestination,
                    onTabPressed = {
                        viewModel.updateCurrentDestination(destinationType = it)
                        viewModel.resetHomeScreenStates()
                    },
                    navigationItemContentList = navigationItemContentList,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun DestinationContent(
    uiState: DestinationUiState,
    onDestinationCardPressed: (Destination) -> Unit,
    modifier: Modifier
) {
    val destinationList = uiState.currentDestinations

    LazyColumn(
        modifier = modifier,
    ) {
        items(destinationList, key = { destination -> destination.id }) { destination ->
            DestinationListItem(
                destination = destination,
                onCardClick = {
                    onDestinationCardPressed(destination)
                },
                modifier = modifier,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DestinationListItem(
    destination: Destination,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(dimensionResource(R.dimen.destination_list_item_vertical_spacing)),
        onClick = onCardClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.destination_list_item_inner_padding)),
        ) {
            Image(
                painter = painterResource(id = destination.image),
                contentDescription = null
            )
            Text(
                text = stringResource(destination.title),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.destination_list_item_header_subject_spacing),
                    bottom = dimensionResource(R.dimen.destination_list_item_subject_body_spacing)
                ),
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = stringResource(destination.title),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = modifier.weight(1f)
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "Rating " + destination.rating.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
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