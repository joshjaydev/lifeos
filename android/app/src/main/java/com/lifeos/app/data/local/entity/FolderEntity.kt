package com.lifeos.app.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

@Entity(tableName = "folders")
data class FolderEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "user_id")
    val userId: String,
    val name: String,
    val color: Long?,
    @ColumnInfo(name = "is_default")
    val isDefault: Boolean = false,
    @ColumnInfo(name = "goal_id")
    val goalId: String?,
    @ColumnInfo(name = "created_at")
    val createdAt: Instant,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Instant
)
