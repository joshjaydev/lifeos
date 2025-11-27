package com.lifeos.domain.model

import kotlinx.datetime.Instant

/**
 * Domain model representing a Notebook for organizing Notes.
 */
data class Notebook(
    val id: String,
    val userId: String,
    val name: String,
    val isDefault: Boolean = false,
    val goalId: String? = null, // Reference to goal if this notebook was auto-created
    val createdAt: Instant,
    val updatedAt: Instant
)
