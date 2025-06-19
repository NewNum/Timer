package com.huxh.apps.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.huxh.apps.core.designsystem.icon.AppIcons

const val Toolbar_Height = 56

@Composable
fun ToolbarComposable(
    modifier: Modifier = Modifier,
    backClick: () -> Unit = {},
    title: String = "",
    showBackButton: Boolean = true,
    backImageVector: ImageVector = AppIcons.ArrowBack,
    content: @Composable BoxScope.() -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(Toolbar_Height.dp)
            .padding(horizontal = 16.dp)
            .then(modifier)
    ) {
        val color = MaterialTheme.colorScheme.tertiary
        if (showBackButton) {
            Icon(
                imageVector = backImageVector,
                contentDescription = "back",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(32.dp)
                    .clickable(onClick = backClick),
                tint = color,
            )
        }
        if (title.isNotEmpty()) {
            Text(
                text = title,
                modifier = Modifier.align(Alignment.CenterStart),
                style = TextStyle.Default.copy(
                    fontSize = 18.sp,
                    color = color,
                )
            )
        }
        content.invoke(this)
    }
}

