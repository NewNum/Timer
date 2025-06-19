package com.huxh.apps.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle


@Composable
fun GeneralButton(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
) {
    Box(modifier = modifier, contentAlignment = contentAlignment) {
        Text(
            text = text,
            style = style
        )
    }
}