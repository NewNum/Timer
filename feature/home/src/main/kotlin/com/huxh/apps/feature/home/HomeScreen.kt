package com.huxh.apps.feature.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        items(300) {
            Text(
                text = "Home"
            )
        }
    }

}
