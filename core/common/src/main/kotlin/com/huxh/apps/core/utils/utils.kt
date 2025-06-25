package com.huxh.apps.core.utils

fun timeDisintegration(totalSeconds: Long): Triple<Int, Int, Int> {
    if (totalSeconds <= 0) return Triple(0, 0, 0)
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60
    return Triple(hours.toInt(), minutes.toInt(), seconds.toInt())
}

fun formatTime(time: Long): String {
    val triple = timeDisintegration(time / 1000L)
    val hour = if (triple.first == 0) {
        ""
    } else {
        "${triple.first}时"
    }
    val minute = if (triple.second == 0) {
        ""
    } else {
        "${triple.second}分"
    }
    val second = if (triple.third == 0) {
        ""
    } else {
        "${triple.third}秒"
    }
    return if (hour.isEmpty() && minute.isEmpty() && second.isEmpty()) {
        "0秒"
    } else {
        hour + minute + second
    }
}

