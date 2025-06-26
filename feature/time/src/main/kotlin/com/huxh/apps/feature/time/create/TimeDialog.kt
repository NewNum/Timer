package com.huxh.apps.feature.time.create

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.huxh.apps.core.ui.BaseFullScreenDialog
import com.huxh.apps.core.ui.GeneralButton
import com.huxh.apps.core.ui.TimePicker
import com.huxh.apps.core.utils.timeDisintegration
import kotlin.time.Duration.Companion.microseconds
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun TimeDialog(
    onDismissRequest: () -> Unit,
    start: Long,
    onConfirm: (Long) -> Unit
) {
    BaseFullScreenDialog(
        onDismissRequest = onDismissRequest,
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .padding(16.dp)
        ) {
            var timeMillis by remember { mutableLongStateOf(start) }
            TimePicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                start = timeDisintegration(timeMillis.milliseconds),
                mask = Color.White,
            ) {
                timeMillis = it
            }
            Spacer(modifier = Modifier.size(16.dp))
            GeneralButton(
                text = "确定",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.surfaceContainer,
                        MaterialTheme.shapes.medium
                    )
                    .clickable(onClick = { onConfirm.invoke(timeMillis) })
                    .padding(vertical = 12.dp)
            )
        }

    }
}
