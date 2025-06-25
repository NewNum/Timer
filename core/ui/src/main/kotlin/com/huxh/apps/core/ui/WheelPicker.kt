package com.huxh.apps.core.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.huxh.apps.core.designsystem.theme.LocalBackgroundTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun <T> WheelPicker(
    visibleItemCount: Int = 3,
    itemHeight: Dp = 56.dp,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    items: List<T>,
    format: (T) -> String,
    onValueSelected: (T) -> Unit
) {
    val visibleItemsCount = if (visibleItemCount % 2 == 0) {
        visibleItemCount + 1
    } else {
        visibleItemCount
    }
    val listState = rememberLazyListState(visibleItemsCount)
    val coroutineScope = rememberCoroutineScope()
    val centerIndex = visibleItemsCount / 2

    val itemHeightPx = with(LocalDensity.current) { itemHeight.toPx() }
    LaunchedEffect(listState) {
        snapshotFlow { listState.isScrollInProgress }
            .collect { isScrolling ->
                if (!isScrolling) {
                    val offset = listState.firstVisibleItemScrollOffset
                    val index = listState.firstVisibleItemIndex
                    // 如果滑动过半则滚动到下一项
                    val snapToIndex = if (offset > itemHeightPx / 2) index + 1 else index
                    coroutineScope.launch {
                        listState.animateScrollToItem(snapToIndex)
                    }
                    val selectedValueIndex = (snapToIndex + centerIndex) % items.size
                    if (selectedValueIndex in items.indices) {
                        onValueSelected(items.elementAt(selectedValueIndex))
                    }
                }
            }
    }
    val size = items.size
    LaunchedEffect(Unit) {
        delay(100L)
        listState.scrollToItem(size * 1000 - 1)
    }
    LazyColumn(
        state = listState,
        modifier = modifier.height(itemHeight * visibleItemsCount),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(Int.MAX_VALUE) { index ->
            GeneralButton(
                text = format(items[index % size]),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeight),
                style = textStyle,
            )
        }
    }
}

@Composable
fun TimePicker(
    modifier: Modifier = Modifier,
    mask: Color = LocalBackgroundTheme.current.color,
    start: Triple<Int, Int, Int> = Triple(0, 0, 0),
    onValueSelected: (Long) -> Unit
) {
    val itemHeight = 60.dp
    val textStyle = TextStyle(
        color = Color.Black,
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
    )
    val visibleItemCount = 3
    var hour by remember() { mutableIntStateOf(start.first) }
    var minute by remember() { mutableIntStateOf(start.second) }
    var second by remember() { mutableIntStateOf(start.third) }
    val hourList = List(24) { it }
    val minuteList = List(60) { it }
    val secondList = List(60) { it }
    val onValueChange = {
        onValueSelected.invoke((((hour * 60L + minute) * 60) + second) * 1000L)
    }
    Box(
        modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            WheelPicker(
                modifier = Modifier.weight(1f),
                visibleItemCount = visibleItemCount,
                itemHeight = itemHeight,
                items = hourList,
                format = { String.format("%02d", it) },
                textStyle = textStyle,
            ) {
                hour
                onValueChange.invoke()
            }
            Text(
                text = ":",
                style = textStyle,
            )
            WheelPicker(
                modifier = Modifier.weight(1f),
                visibleItemCount = visibleItemCount,
                itemHeight = itemHeight,
                items = minuteList,
                format = { String.format("%02d", it) },
                textStyle = textStyle,
            ) {
                minute
                onValueChange.invoke()
            }
            Text(
                text = ":",
                style = textStyle,
            )
            WheelPicker(
                modifier = Modifier.weight(1f),
                visibleItemCount = visibleItemCount,
                itemHeight = itemHeight,
                items = secondList,
                format = { String.format("%02d", it) },
                textStyle = textStyle,
            ) {
                second = it
                onValueChange.invoke()
            }
        }
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeight * (visibleItemCount / 2))
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                mask,
                                mask.copy(alpha = 0f),
                            ),
                        ),
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeight)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeight * (visibleItemCount / 2))
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                mask.copy(alpha = 0f),
                                mask,
                            ),
                        ),
                    )
            )
        }
    }

}

