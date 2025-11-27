package com.lifeos.app.data.local.dao

import androidx.room.*
import com.lifeos.app.data.local.entity.FolderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderDao {
    @Query("SELECT * FROM folders WHERE user_id = :userId ORDER BY is_default DESC, name ASC")
    fun getFolders(userId: String): Flow<List<FolderEntity>>

    @Query("SELECT * FROM folders WHERE id = :id")
    fun getFolder(id: String): Flow<FolderEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(folder: FolderEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(folders: List<FolderEntity>)

    @Update
    suspend fun update(folder: FolderEntity)

    @Query("DELETE FROM folders WHERE id = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM folders WHERE user_id = :userId")
    suspend fun deleteAll(userId: String)
}
