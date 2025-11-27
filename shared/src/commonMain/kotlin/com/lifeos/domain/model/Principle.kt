package com.lifeos.domain.model

import kotlinx.datetime.Instant

/**
 * Domain model representing a Principle (a fundamental truth learned from experience).
 * Supports hierarchical structure via parentId for sub-principles.
 */
data class Principle(
    val id: String,
    val userId: String,
    val parentId: String? = null, // For hierarchical principles
    val fundamentalTruth: String,
    val experience: String? = null, // The failures/experiences that led to this principle
    val createdAt: Instant,
    val updatedAt: Instant
)
