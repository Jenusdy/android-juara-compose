package io.github.jenusdy.mars_photos.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.jenusdy.mars_photos.R
import io.github.jenusdy.mars_photos.ui.theme.MarsphotosTheme

@Composable
fun HomeScreen(
    marsUiState: String,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    ResultScreen(
        marsUiState,
        modifier.padding(top = contentPadding.calculateTopPadding())
    )
}

@Composable
fun ResultScreen(
    photos: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = photos)
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomeScreenPreview() {
    MarsphotosTheme {
        ResultScreen(photos = stringResource(id = R.string.placeholder_result))
    }
}

