package com.lifeos.domain.model

import kotlinx.datetime.Instant

/**
 * Domain model representing a Calendar Event.
 */
data class Event(
    val id: String,
    val userId: String,
    val title: String,
    val description: String? = null,
    val startDatetime: Instant,
    val endDatetime: Instant,
    val isRecurring: Boolean = false,
    val recurrenceType: RecurrenceType? = null,
    val notifyBefore: Int = 30, // minutes before event
    val createdAt: Instant,
    val updatedAt: Instant
)
