package com.lifeos.domain.model

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalTime

/**
 * Domain model representing an Action/Task in the user's to-do system.
 * This is a pure Kotlin data class with no platform dependencies.
 */
data class Action(
    val id: String,
    val userId: String,
    val folderId: String,
    val title: String,
    val dueDate: Instant? = null,
    val dueTime: LocalTime? = null,
    val isRecurring: Boolean = false,
    val recurrenceType: RecurrenceType? = null,
    val notifyBefore: Int = 60, // minutes before due date
    val createdAt: Instant,
    val updatedAt: Instant
)

/**
 * Enum representing recurrence types for actions
 */
enum class RecurrenceType {
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY;

    companion object {
        fun fromString(value: String?): RecurrenceType? = when (value?.lowercase()) {
            "daily" -> DAILY
            "weekly" -> WEEKLY
            "monthly" -> MONTHLY
            "yearly" -> YEARLY
            else -> null
        }
    }

    fun toStorageString(): String = name.lowercase()
}
