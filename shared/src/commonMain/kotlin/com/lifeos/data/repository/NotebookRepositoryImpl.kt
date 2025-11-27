package com.lifeos.data.repository

import com.lifeos.data.local.LocalDatabase
import com.lifeos.domain.model.Notebook
import com.lifeos.domain.repository.NotebookRepository
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class NotebookRepositoryImpl(
    private val localDatabase: LocalDatabase
) : NotebookRepository {

    override fun getNotebooks(userId: String): Flow<Result<List<Notebook>>> =
        localDatabase.getNotebooks(userId)
            .map<List<Notebook>, Result<List<Notebook>>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override fun getNotebookById(id: String): Flow<Result<Notebook?>> =
        localDatabase.getNotebookById(id)
            .map<Notebook?, Result<Notebook?>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override suspend fun createNotebook(notebook: Notebook): Result<Unit> = try {
        localDatabase.insertNotebook(notebook)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun updateNotebook(notebook: Notebook): Result<Unit> = try {
        localDatabase.updateNotebook(notebook)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun deleteNotebook(id: String): Result<Unit> = try {
        localDatabase.deleteNotebook(id)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }
}
