package com.huxh.apps.feature.time.start

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.huxh.apps.core.utils.formatTime
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

data class TimeNode(
    val time: Duration,
    val onNodeReached: () -> Unit = {}
)

@Composable
fun CountdownCircle(
    isRunning: Boolean = false,
    totalTime: Duration = 10.seconds,
    repeatCount: Int = 1, // 默认只执行1次
    circleSize: Dp = 120.dp,
    strokeWidth: Dp = 8.dp,
    backgroundColor: Color = Color.LightGray.copy(alpha = 0.3f),
    progressColor: Color = Color.Blue,
    nodeColor: Color = Color.White,
    nodeSize: Dp = 4.dp,
    timeNodes: List<TimeNode> = emptyList(),
    textStyle: TextStyle = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    ),
    onCountdownFinish: (currentRound: Int, totalRounds: Int) -> Unit = { _, _ -> },
    onAllRoundsComplete: () -> Unit = {}
) {
    var remainingTime by remember { mutableStateOf(totalTime) }
    var currentRound by remember { mutableStateOf(1) }

    val sortedNodes = remember(timeNodes) {
        timeNodes.sortedByDescending { it.time }
    }

    val currentProgress = remember(remainingTime, totalTime) {
        remainingTime.inWholeMilliseconds / totalTime.inWholeMilliseconds.toFloat()
    }

    val animationProgress by animateFloatAsState(
        targetValue = currentProgress,
        animationSpec = tween(durationMillis = 300, easing = LinearEasing)
    )

    LaunchedEffect(isRunning) {
        // 初始检查
        checkCurrentNode(remainingTime, sortedNodes)?.let { node ->
            // 首次启动可能已处于某个节点
        }

        while (remainingTime > Duration.ZERO && isRunning) {
            delay(1000)
            remainingTime = remainingTime - 1.seconds

            // 检查是否到达新节点
            checkCurrentNode(remainingTime, sortedNodes)?.let { node ->
                node.onNodeReached()
            }

            if (remainingTime <= Duration.ZERO) {
                // 一轮倒计时结束
                onCountdownFinish(currentRound, repeatCount)

                if (currentRound < repeatCount) {
                    // 准备下一轮
                    delay(500) // 轮次之间的短暂间隔
                    remainingTime = totalTime
                    currentRound++
                } else {
                    // 所有轮次完成
                    onAllRoundsComplete()
                }
            }
        }
    }

    Box(
        modifier = Modifier.size(circleSize),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val radius = (size.minDimension - strokeWidth.toPx()) / 2
            val center = Offset(size.width / 2, size.height / 2)

            // 绘制背景圆环
            drawArc(
                color = backgroundColor,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round),
                size = Size(size.width - strokeWidth.toPx(), size.height - strokeWidth.toPx()),
                topLeft = Offset(strokeWidth.toPx() / 2, strokeWidth.toPx() / 2)
            )

            // 绘制进度圆环
            drawArc(
                color = progressColor,
                startAngle = -90f,
                sweepAngle = 360f * animationProgress,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round),
                size = Size(size.width - strokeWidth.toPx(), size.height - strokeWidth.toPx()),
                topLeft = Offset(strokeWidth.toPx() / 2, strokeWidth.toPx() / 2)
            )

            // 绘制节点标记
            val nodeRadius = nodeSize.toPx()
            sortedNodes.forEach { node ->
                val nodeProgress =
                    node.time.inWholeMilliseconds / totalTime.inWholeMilliseconds.toFloat()
                val angle = (360f * nodeProgress) - 90f

                val angleRadians = angle * (Math.PI / 180.0).toFloat()
                val nodeX = center.x + radius * cos(angleRadians)
                val nodeY = center.y + radius * sin(angleRadians)

                drawCircle(
                    color = nodeColor,
                    radius = nodeRadius,
                    center = Offset(nodeX, nodeY)
                )
            }
        }

        Text(
            text = formatTime(remainingTime),
            style = textStyle
        )

    }
}

private fun checkCurrentNode(
    remainingTime: Duration,
    nodes: List<TimeNode>
): TimeNode? {
    return nodes.firstOrNull { remainingTime <= it.time }
}


@Preview(showBackground = true)
@Composable
fun CountdownCircleWithRepeatPreview() {
    CountdownCircle(
        totalTime = 10.seconds,
        repeatCount = 3,
        circleSize = 150.dp,
        strokeWidth = 10.dp,
        progressColor = Color.Blue,
        nodeColor = Color.White,
        timeNodes = listOf(
            TimeNode(5.seconds) {
                println("已到达中间节点")
            }
        )
    )
}