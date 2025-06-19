package com.huxh.apps.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun InfiniteNumberPicker(
    valueRange: IntRange,
    modifier: Modifier = Modifier,
    visibleItemCount: Int = 5,
    initialValue: Int = valueRange.first,
    itemHeight: Dp = 40.dp,
    textStyle: TextStyle = LocalTextStyle.current.copy(fontSize = 24.sp),
    selectedTextStyle: TextStyle = textStyle.copy(fontSize = 32.sp, fontWeight = FontWeight.Bold),
    onValueChange: (Int) -> Unit
) {
    val repeatCount = 1000
    val repeatedItems = remember { List(repeatCount * valueRange.count()) { valueRange.first + (it % valueRange.count()) } }
    val totalItemCount = repeatedItems.size
    val centerIndex = visibleItemCount / 2

    val startIndex = totalItemCount / 2 - centerIndex - (initialValue - valueRange.first)
    val listState = rememberLazyListState(startIndex)
    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current

    // 自动归位逻辑
    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress) {
            val offsetPx = with(density) { itemHeight.toPx() }
            val offset = listState.firstVisibleItemScrollOffset
            val index = listState.firstVisibleItemIndex
            val snapIndex = if (offset > offsetPx / 2) index + 1 else index

            coroutineScope.launch {
                listState.animateScrollToItem(snapIndex)
            }

            val value = repeatedItems.getOrNull(snapIndex + centerIndex)
            value?.let { onValueChange(it % valueRange.count() + valueRange.first) }
        }
    }

    // 滚动接近边界时重置到中间
    LaunchedEffect(Unit) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                if (index < valueRange.count() || index > totalItemCount - valueRange.count()) {
                    coroutineScope.launch {
                        listState.scrollToItem(totalItemCount / 2)
                    }
                }
            }
    }

    // 渐变遮罩 + 内容组合
    Box(
        modifier = modifier
            .height(itemHeight * visibleItemCount)
            .width(80.dp)
            .clipToBounds()
    ) {
        // 内容区域
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(vertical = itemHeight * centerIndex),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(repeatedItems) { index, value ->
                val distance = (index - listState.firstVisibleItemIndex - centerIndex).absoluteValue
                val style = when (distance) {
                    0 -> selectedTextStyle
                    1 -> textStyle.copy(fontSize = textStyle.fontSize * 0.85)
                    2 -> textStyle.copy(fontSize = textStyle.fontSize * 0.7)
                    else -> textStyle.copy(fontSize = textStyle.fontSize * 0.6)
                }

                Text(
                    text = value.toString().padStart(2, '0'),
                    style = style,
                    modifier = Modifier
                        .height(itemHeight)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }

        // 上遮罩
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeight * centerIndex)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.White, Color.Transparent)
                    )
                )
                .align(Alignment.TopCenter)
        )

        // 下遮罩
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeight * centerIndex)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.White)
                    )
                )
                .align(Alignment.BottomCenter)
        )

        // 中心选中背景
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .height(itemHeight)
                .fillMaxWidth()
                .background(Color.LightGray.copy(alpha = 0.15f))
        )
    }
}