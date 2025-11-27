package com.lifeos.data.repository

import com.lifeos.data.local.LocalDatabase
import com.lifeos.domain.model.Profile
import com.lifeos.domain.repository.ProfileRepository
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class ProfileRepositoryImpl(
    private val localDatabase: LocalDatabase
) : ProfileRepository {

    override fun getProfile(userId: String): Flow<Result<Profile?>> =
        localDatabase.getProfile(userId)
            .map<Profile?, Result<Profile?>> { Result.Success(it) }
            .catch { emit(Result.Error(Exception(it))) }

    override suspend fun createProfile(profile: Profile): Result<Unit> = try {
        localDatabase.insertProfile(profile)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun updateProfile(profile: Profile): Result<Unit> = try {
        localDatabase.updateProfile(profile)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun deleteProfile(userId: String): Result<Unit> = try {
        localDatabase.deleteProfile(userId)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }
}
