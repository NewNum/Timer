package com.huxh.apps.feature.time.start

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.huxh.apps.core.database.model.ProjectEntity
import com.huxh.apps.core.designsystem.component.AppBackground
import com.huxh.apps.core.ui.ToolbarComposable
import com.huxh.apps.core.utils.formatTime

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
            Content(project)
        }
    }

}

@Composable
private fun Content(projectEntity: ProjectEntity?) {
    if (projectEntity == null) return
    Spacer(modifier = Modifier.size(20.dp))
    Text(
        text = projectEntity.name
    )
    if (projectEntity.description.isNotEmpty()) {
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = projectEntity.description
        )
    }
    Spacer(modifier = Modifier.size(20.dp))
    Text(
        text = "单次时长：${formatTime(projectEntity.duration)}"
    )
    Spacer(modifier = Modifier.size(20.dp))
    Text(
        text = "总次数：${projectEntity.repeatCount}"
    )
    Spacer(modifier = Modifier.size(20.dp))

}