package com.lifeos.app.data.mapper

import com.lifeos.app.data.local.entity.NoteEntity
import com.lifeos.domain.model.Note

/**
 * Mapper functions for converting between NoteEntity and Note domain model
 */

fun NoteEntity.toDomain(): Note = Note(
    id = id,
    userId = userId,
    notebookId = notebookId,
    title = title,
    content = content,
    isJournal = isJournal,
    journalDate = journalDate,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Note.toEntity(): NoteEntity = NoteEntity(
    id = id,
    userId = userId,
    notebookId = notebookId,
    title = title,
    content = content,
    isJournal = isJournal,
    journalDate = journalDate,
    createdAt = createdAt,
    updatedAt = updatedAt
)
