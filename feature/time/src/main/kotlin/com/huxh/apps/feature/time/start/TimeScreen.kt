package com.huxh.apps.feature.time.start

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.huxh.apps.core.database.model.ProjectEntity
import com.huxh.apps.core.designsystem.component.AppBackground
import com.huxh.apps.core.ui.GeneralButton
import com.huxh.apps.core.ui.ToolbarComposable
import java.nio.file.WatchEvent
import kotlin.time.Duration.Companion.microseconds
import kotlin.time.Duration.Companion.milliseconds

@Composable
internal fun TimeScreen(
    navigateUp: () -> Unit,
    navigateToCreateTime: (Long) -> Unit,
    viewModel: TimeViewModel = hiltViewModel(),
) {
    AppBackground(color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ToolbarComposable(
                backClick = navigateUp,
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.CenterEnd)
                        .clickable(
                            onClick = { navigateToCreateTime.invoke(viewModel.projectId) },
                        )
                )
            }
            val project by viewModel.project.collectAsState()
            Content(project, viewModel)
        }
    }

}

@Composable
private fun Content(projectEntity: ProjectEntity?, viewModel: TimeViewModel) {
    if (projectEntity == null) return
    val tasks by viewModel.tasks.collectAsState()
    var repeat by remember { mutableIntStateOf(projectEntity.repeatCount) }
    var isRunning by remember { mutableStateOf(false) }
    Spacer(modifier = Modifier.size(20.dp))
    Text(
        text = projectEntity.name,
        style = MaterialTheme.typography.titleMedium
    )
    if (projectEntity.description.isNotEmpty()) {
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = projectEntity.description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
    Spacer(modifier = Modifier.size(20.dp))
    CountdownCircle(
        isRunning = isRunning,
        totalTime = projectEntity.duration.milliseconds,
        repeatCount = projectEntity.repeatCount,
        circleSize = 200.dp,
        strokeWidth = 10.dp,
        progressColor = Color.Blue,
        nodeColor = Color.White,
        timeNodes = tasks.map {
            TimeNode(it.duration.milliseconds)
        },
        onCountdownFinish = { currentRound: Int, totalRounds: Int ->
            repeat = totalRounds - currentRound
        },
        onAllRoundsComplete = {
            isRunning = false
            repeat = projectEntity.repeatCount
        }

    )
    Spacer(modifier = Modifier.size(20.dp))
    Text(
        text = "×${repeat} / ${projectEntity.repeatCount}"
    )


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        GeneralButton(
            text = "暂停",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 12.dp)
                .clickable(onClick = { isRunning = false }, enabled = isRunning)
        )
        Spacer(modifier = Modifier.size(4.dp))
        GeneralButton(
            text = if (isRunning) "停止" else "开始",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 12.dp)
                .clickable(
                    onClick = {
                        if (isRunning) {
                            isRunning = false
                        } else {
                            isRunning = true
                        }

                    },
                )
        )
    }

}