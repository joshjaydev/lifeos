package com.lifeos.domain.model

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

/**
 * Domain model representing a completed action.
 * Tracks when recurring or one-time actions are completed.
 */
data class ActionCompletion(
    val id: String,
    val actionId: String,
    val completedDate: LocalDate,
    val completedAt: Instant
)
