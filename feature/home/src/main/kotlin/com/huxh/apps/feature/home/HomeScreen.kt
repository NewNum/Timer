package com.huxh.apps.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.huxh.apps.core.designsystem.component.AppBackground
import com.huxh.apps.core.designsystem.icon.AppIcons
import com.huxh.apps.core.designsystem.theme.LocalBackgroundTheme
import com.huxh.apps.core.ui.InfiniteNumberPicker

@Composable
internal fun HomeScreen(
    navigateToCreateTime: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    AppBackground(color = Color.White) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .statusBarsPadding(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                var minute by remember { mutableIntStateOf(0) }
                InfiniteNumberPicker(
                    valueRange = 0..59,
                    initialValue = 30,
                    onValueChange = { minute = it }
                )
                Text(
                    text = "剩余时间：${minute}"
                )
            }
            Icon(
                imageVector = AppIcons.Add,
                contentDescription = "Add",
                tint = Color.Black,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(20.dp)
                    .size(60.dp)
                    .background(color = LocalBackgroundTheme.current.color)
                    .clip(shape = CircleShape)
                    .clickable(onClick = navigateToCreateTime)
            )
        }


    }

}
