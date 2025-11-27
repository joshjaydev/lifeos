package com.lifeos.data.repository

import com.lifeos.data.local.LocalDatabase
import com.lifeos.domain.model.Folder
import com.lifeos.domain.repository.FolderRepository
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class FolderRepositoryImpl(
    private val localDatabase: LocalDatabase
) : FolderRepository {

    override fun getFolders(userId: String): Flow<Result<List<Folder>>> =
        localDatabase.getFolders(userId)
            .map<List<Folder>, Result<List<Folder>>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override fun getFolderById(id: String): Flow<Result<Folder?>> =
        localDatabase.getFolderById(id)
            .map<Folder?, Result<Folder?>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override suspend fun createFolder(folder: Folder): Result<Unit> = try {
        localDatabase.insertFolder(folder)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun updateFolder(folder: Folder): Result<Unit> = try {
        localDatabase.updateFolder(folder)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun deleteFolder(id: String): Result<Unit> = try {
        localDatabase.deleteFolder(id)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }
}
