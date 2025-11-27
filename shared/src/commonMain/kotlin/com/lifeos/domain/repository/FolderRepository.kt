package com.lifeos.domain.repository

import com.lifeos.domain.model.Folder
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for Folder operations.
 */
interface FolderRepository {
    fun getFolders(userId: String): Flow<Result<List<Folder>>>
    fun getFolderById(id: String): Flow<Result<Folder?>>
    suspend fun createFolder(folder: Folder): Result<Unit>
    suspend fun updateFolder(folder: Folder): Result<Unit>
    suspend fun deleteFolder(id: String): Result<Unit>
}
