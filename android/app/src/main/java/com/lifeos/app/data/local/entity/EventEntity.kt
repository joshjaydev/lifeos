package com.lifeos.app.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "user_id")
    val userId: String,
    val title: String,
    val description: String?,
    @ColumnInfo(name = "start_datetime")
    val startDatetime: Instant,
    @ColumnInfo(name = "end_datetime")
    val endDatetime: Instant,
    @ColumnInfo(name = "is_recurring")
    val isRecurring: Boolean = false,
    @ColumnInfo(name = "recurrence_type")
    val recurrenceType: String?,
    @ColumnInfo(name = "notify_before")
    val notifyBefore: Int = 30,
    @ColumnInfo(name = "created_at")
    val createdAt: Instant,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Instant
)
