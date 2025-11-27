package com.lifeos.domain.model

import kotlinx.datetime.Instant

/**
 * Domain model representing a Folder for organizing Actions.
 */
data class Folder(
    val id: String,
    val userId: String,
    val name: String,
    val color: Long? = null, // Color for the folder icon (ARGB long value)
    val isDefault: Boolean = false,
    val goalId: String? = null, // Reference to goal if this folder was auto-created
    val createdAt: Instant,
    val updatedAt: Instant
)
