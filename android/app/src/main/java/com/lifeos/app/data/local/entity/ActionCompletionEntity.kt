package com.lifeos.app.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

@Entity(tableName = "action_completions")
data class ActionCompletionEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "action_id")
    val actionId: String,
    @ColumnInfo(name = "completed_date")
    val completedDate: LocalDate,
    @ColumnInfo(name = "completed_at")
    val completedAt: Instant
)
