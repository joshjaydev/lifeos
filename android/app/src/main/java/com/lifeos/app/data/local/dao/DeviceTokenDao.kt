package com.lifeos.app.data.local.dao

import androidx.room.*
import com.lifeos.app.data.local.entity.DeviceTokenEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceTokenDao {
    @Query("SELECT * FROM device_tokens WHERE user_id = :userId")
    fun getTokens(userId: String): Flow<List<DeviceTokenEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(token: DeviceTokenEntity)

    @Query("DELETE FROM device_tokens WHERE id = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM device_tokens WHERE user_id = :userId")
    suspend fun deleteAll(userId: String)
}
