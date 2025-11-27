package com.lifeos.data.repository

import com.lifeos.data.local.LocalDatabase
import com.lifeos.domain.model.Note
import com.lifeos.domain.repository.NoteRepository
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate

class NoteRepositoryImpl(
    private val localDatabase: LocalDatabase
) : NoteRepository {

    override fun getNotes(userId: String): Flow<Result<List<Note>>> =
        localDatabase.getNotes(userId)
            .map<List<Note>, Result<List<Note>>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override fun getNotesByNotebook(notebookId: String): Flow<Result<List<Note>>> =
        localDatabase.getNotesByNotebook(notebookId)
            .map<List<Note>, Result<List<Note>>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override fun getNoteById(id: String): Flow<Result<Note?>> =
        localDatabase.getNoteById(id)
            .map<Note?, Result<Note?>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override fun getJournalNote(notebookId: String, date: LocalDate): Flow<Result<Note?>> =
        localDatabase.getJournalNote(notebookId, date)
            .map<Note?, Result<Note?>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override suspend fun createNote(note: Note): Result<Unit> = try {
        localDatabase.insertNote(note)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun updateNote(note: Note): Result<Unit> = try {
        localDatabase.updateNote(note)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun deleteNote(id: String): Result<Unit> = try {
        localDatabase.deleteNote(id)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }
}
