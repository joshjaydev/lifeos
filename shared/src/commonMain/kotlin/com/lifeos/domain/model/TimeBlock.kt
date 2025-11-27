package com.lifeos.domain.model

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

/**
 * Domain model representing a TimeBlock - links an Action to a specific time slot.
 */
data class TimeBlock(
    val id: String,
    val userId: String,
    val actionId: String,
    val blockDate: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val createdAt: Instant
)
