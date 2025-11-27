package com.lifeos.app.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

@Entity(tableName = "time_blocks")
data class TimeBlockEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "user_id")
    val userId: String,
    @ColumnInfo(name = "action_id")
    val actionId: String,
    @ColumnInfo(name = "block_date")
    val blockDate: LocalDate,
    @ColumnInfo(name = "start_time")
    val startTime: LocalTime,
    @ColumnInfo(name = "end_time")
    val endTime: LocalTime,
    @ColumnInfo(name = "created_at")
    val createdAt: Instant
)
