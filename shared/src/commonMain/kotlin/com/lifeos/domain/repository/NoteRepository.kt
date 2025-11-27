package com.lifeos.domain.repository

import com.lifeos.domain.model.Note
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

/**
 * Repository interface for Note operations.
 */
interface NoteRepository {
    fun getNotes(userId: String): Flow<Result<List<Note>>>
    fun getNotesByNotebook(notebookId: String): Flow<Result<List<Note>>>
    fun getNoteById(id: String): Flow<Result<Note?>>
    fun getJournalNote(notebookId: String, date: LocalDate): Flow<Result<Note?>>
    suspend fun createNote(note: Note): Result<Unit>
    suspend fun updateNote(note: Note): Result<Unit>
    suspend fun deleteNote(id: String): Result<Unit>
}
