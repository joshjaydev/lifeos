package com.lifeos.domain.model

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

/**
 * Domain model representing a Note.
 * Content is stored as plain text or JSON for rich text support.
 */
data class Note(
    val id: String,
    val userId: String,
    val notebookId: String,
    val title: String,
    val content: String, // Can be plain text or JSON for rich text
    val isJournal: Boolean = false,
    val journalDate: LocalDate? = null,
    val createdAt: Instant,
    val updatedAt: Instant
)
