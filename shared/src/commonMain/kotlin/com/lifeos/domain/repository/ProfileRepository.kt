package com.lifeos.domain.repository

import com.lifeos.domain.model.Profile
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for Profile operations.
 */
interface ProfileRepository {
    fun getProfile(userId: String): Flow<Result<Profile?>>
    suspend fun createProfile(profile: Profile): Result<Unit>
    suspend fun updateProfile(profile: Profile): Result<Unit>
    suspend fun deleteProfile(userId: String): Result<Unit>
}
