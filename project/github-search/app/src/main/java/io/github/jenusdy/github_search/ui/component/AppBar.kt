package io.github.jenusdy.github_search.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import io.github.jenusdy.github_search.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String = stringResource(R.string.app_name)) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                title,
                textAlign = TextAlign.Center
            )
        },
        modifier = Modifier.fillMaxWidth(),
    )
}