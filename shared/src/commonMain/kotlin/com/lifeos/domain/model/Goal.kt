package com.lifeos.domain.model

import kotlinx.datetime.Instant

/**
 * Domain model representing a Goal.
 * When a goal is created, it automatically creates a Folder and Notebook.
 */
data class Goal(
    val id: String,
    val userId: String,
    val whatDoYouWant: String, // The goal itself - answer to "What do you want?"
    val folderId: String? = null, // Auto-created folder reference
    val notebookId: String? = null, // Auto-created notebook reference
    val createdAt: Instant,
    val updatedAt: Instant
)
