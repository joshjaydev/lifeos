package com.lifeos.domain.model

import kotlinx.datetime.Instant

/**
 * Domain model representing a User Profile.
 */
data class Profile(
    val id: String,
    val email: String? = null,
    val displayName: String? = null,
    val avatarUrl: String? = null,
    val timeBlockSize: Int = 10, // Default time block size in minutes
    val createdAt: Instant,
    val updatedAt: Instant
)
