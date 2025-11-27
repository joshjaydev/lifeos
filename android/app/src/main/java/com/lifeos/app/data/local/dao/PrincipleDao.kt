package com.lifeos.app.data.local.dao

import androidx.room.*
import com.lifeos.app.data.local.entity.PrincipleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PrincipleDao {
    @Query("SELECT * FROM principles WHERE user_id = :userId ORDER BY created_at ASC")
    fun getPrinciples(userId: String): Flow<List<PrincipleEntity>>

    @Query("SELECT * FROM principles WHERE parent_id = :parentId ORDER BY created_at ASC")
    fun getSubPrinciples(parentId: String): Flow<List<PrincipleEntity>>

    @Query("SELECT * FROM principles WHERE id = :id")
    fun getPrinciple(id: String): Flow<PrincipleEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(principle: PrincipleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(principles: List<PrincipleEntity>)

    @Update
    suspend fun update(principle: PrincipleEntity)

    @Query("DELETE FROM principles WHERE id = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM principles WHERE user_id = :userId")
    suspend fun deleteAll(userId: String)
}
