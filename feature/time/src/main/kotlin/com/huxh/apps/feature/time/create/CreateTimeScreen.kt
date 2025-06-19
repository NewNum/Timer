package com.huxh.apps.feature.time.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.huxh.apps.core.designsystem.component.AppBackground
import com.huxh.apps.core.ui.InfiniteNumberPicker
import com.huxh.apps.core.ui.TimePicker
import com.huxh.apps.core.ui.ToolbarComposable

@Composable
internal fun CreateTimeScreen(
    navigateUp: () -> Unit,
    viewModel: CreateTimeViewModel = hiltViewModel(),
) {
    AppBackground(color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
        ) {
            ToolbarComposable(
                backClick = navigateUp,
            )
            var timeMillis by remember { mutableLongStateOf(0L) }
            TimePicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                mask = Color.White,
            ) {
                timeMillis = it
            }
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = "剩余时间：${timeMillis}"
            )

            var minute by remember { mutableStateOf(0) }

            InfiniteNumberPicker(
                valueRange = 0..59,
                initialValue = 30,
                onValueChange = { minute = it }
            )
        }
    }

}
