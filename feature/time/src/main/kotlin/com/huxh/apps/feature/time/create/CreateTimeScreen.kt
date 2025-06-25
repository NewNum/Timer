package com.huxh.apps.feature.time.create

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.huxh.apps.core.designsystem.component.AppBackground
import com.huxh.apps.core.ui.DraggableListState
import com.huxh.apps.core.ui.ToolbarComposable
import com.huxh.apps.core.ui.dragHandle
import com.huxh.apps.core.ui.rememberDraggableListState
import com.huxh.apps.core.utils.formatTime

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
            ) {
                Box(modifier = Modifier.align(Alignment.CenterEnd)) {
                    val loading by viewModel.loading.collectAsState()
                    if (loading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(20.dp),
                            strokeCap = StrokeCap.Round,
                            trackColor = Color.Transparent,
                            color = MaterialTheme.typography.titleSmall.color
                        )
                    } else {
                        Text(
                            text = "保存",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.clickable(onClick = {

                            })
                        )
                    }
                }
            }
            var title by remember { mutableStateOf("") }
            TextField(
                value = title,
                onValueChange = {
                    title = it
                },
                singleLine = true,
                label = { Text("标题") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),

                )
            var desc by remember { mutableStateOf("") }
            TextField(
                value = desc,
                onValueChange = {
                    desc = it
                },
                maxLines = 3,
                label = { Text("描述") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
            val list = remember { mutableStateListOf<Long>() }
            var repeat by remember { mutableIntStateOf(1) }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "单轮时长：${formatTime(list.sum())}",
                    style = MaterialTheme.typography.titleSmall,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "×",
                    style = MaterialTheme.typography.titleSmall,
                )
                Spacer(modifier = Modifier.size(4.dp))
                BasicTextField(
                    value = repeat.toString(),
                    onValueChange = {
                        repeat = it.toIntOrNull() ?: 1
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier
                        .widthIn(min = 20.dp)
                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                        .padding(8.dp)
                )
            }
            val dragDropState = rememberDraggableListState(
                onMove = { from, to ->
                    // 更新数据源顺序
                    val fromItem = list[from]
                    list.removeAt(from)
                    list.add(to, fromItem)
                }
            )
            LazyColumn(
                state = dragDropState.listState,
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    count = list.size,
                    key = { it },
                    itemContent = { index ->
                        var showAdd by remember { mutableStateOf<Long?>(null) }
                        if (showAdd != null) {
                            TimeDialog(
                                onDismissRequest = { showAdd = 0L },
                                start = showAdd!!,
                            ) {
                                if (it == 0L) {
                                    list.removeAt(index)
                                } else {
                                    list[index] = it
                                }
                            }
                        }
                        val item = list[index]
                        // 应用拖拽修饰符
                        LazyListItem(
                            item = item,
                            index = index,
                            dragDropState = dragDropState,
                            onClick = {
                                showAdd = item
                            }
                        )
                    }
                )
                item {
                    var showAdd by remember { mutableStateOf<Boolean>(false) }
                    if (showAdd) {
                        TimeDialog(
                            onDismissRequest = { showAdd = false },
                            start = 0,
                        ) {
                            list.add(it)
                        }
                    }
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            "添加",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .padding(10.dp)
                                .clickable(onClick = { showAdd = true })
                        )
                    }
                }
            }
        }
    }

}

@Composable
private fun LazyListItem(
    item: Long,
    index: Int,
    dragDropState: DraggableListState,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .dragHandle(
                state = dragDropState,
                index = index,
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        onClick.invoke()
                    }
                )
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    // 处理拖拽逻辑
                }
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = formatTime(item),
                modifier = Modifier.weight(1f)
            )
            // 添加拖拽手柄图标（可选）
            Icon(
                imageVector = Icons.Default.DragHandle,
                contentDescription = "拖拽",
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}