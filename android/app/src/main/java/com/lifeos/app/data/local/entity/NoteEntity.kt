package com.lifeos.app.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "user_id")
    val userId: String,
    @ColumnInfo(name = "notebook_id")
    val notebookId: String,
    val title: String,
    val content: String, // JSON string
    @ColumnInfo(name = "is_journal")
    val isJournal: Boolean = false,
    @ColumnInfo(name = "journal_date")
    val journalDate: LocalDate?,
    @ColumnInfo(name = "created_at")
    val createdAt: Instant,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Instant
)
