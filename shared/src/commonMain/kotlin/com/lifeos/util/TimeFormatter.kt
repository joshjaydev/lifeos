package com.lifeos.util

import kotlinx.datetime.LocalTime

fun LocalTime.toAmPmString(): String {
    val hour = this.hour
    val minute = this.minute
    val ampm = if (hour < 12) "AM" else "PM"
    val formattedHour = when {
        hour == 0 -> 12 // 00:xx is 12 AM
        hour > 12 -> hour - 12
        else -> hour
    }
    return String.format("%d:%02d %s", formattedHour, minute, ampm)
}