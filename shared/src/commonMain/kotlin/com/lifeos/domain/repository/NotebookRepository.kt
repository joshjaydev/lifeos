package com.lifeos.domain.repository

import com.lifeos.domain.model.Notebook
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for Notebook operations.
 */
interface NotebookRepository {
    fun getNotebooks(userId: String): Flow<Result<List<Notebook>>>
    fun getNotebookById(id: String): Flow<Result<Notebook?>>
    suspend fun createNotebook(notebook: Notebook): Result<Unit>
    suspend fun updateNotebook(notebook: Notebook): Result<Unit>
    suspend fun deleteNotebook(id: String): Result<Unit>
}
