package com.lifeos.app.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalTime

@Entity(tableName = "actions")
data class ActionEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "user_id")
    val userId: String,
    @ColumnInfo(name = "folder_id")
    val folderId: String,
    val title: String,
    @ColumnInfo(name = "due_date")
    val dueDate: Instant?,
    @ColumnInfo(name = "due_time")
    val dueTime: LocalTime?,
    @ColumnInfo(name = "is_recurring")
    val isRecurring: Boolean = false,
    @ColumnInfo(name = "recurrence_type")
    val recurrenceType: String?, // daily, weekly, monthly, yearly
    @ColumnInfo(name = "notify_before")
    val notifyBefore: Int = 60,
    @ColumnInfo(name = "created_at")
    val createdAt: Instant,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Instant
)
