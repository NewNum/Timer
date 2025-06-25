package com.huxh.apps.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.huxh.apps.core.designsystem.component.AppBackground
import com.huxh.apps.core.designsystem.icon.AppIcons
import com.huxh.apps.core.designsystem.theme.LocalBackgroundTheme
import com.huxh.apps.core.utils.formatTime

@Composable
internal fun HomeScreen(
    navigateToCreateTime: () -> Unit,
    navigateToTime: (Long) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    AppBackground(color = Color.White) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .statusBarsPadding(),
        ) {
            TimeList(viewModel, navigateToTime)
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

@Composable
private fun TimeList(viewModel: HomeViewModel, navigateToTime: (Long) -> Unit) {
    val projectList by viewModel.projects.collectAsState()
    if (projectList.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "暂无计时",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            itemsIndexed(projectList) { _, data ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .shadow(3.dp)
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .clickable(onClick = {
                            navigateToTime.invoke(data.id)
                        })
                        .padding(10.dp),
                ) {
                    Text(
                        text = data.name,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    if (data.description.isNotBlank()) {
                        Text(
                            text = data.description,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                    }
                    Row {
                        Text(
                            text = formatTime(data.duration),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Spacer(modifier = Modifier.size(3.dp))
                        Text(
                            text = "×${data.repeatCount}",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }

                }
            }
        }
    }

}
