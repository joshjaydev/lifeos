package com.lifeos.domain.usecase.folder

import com.lifeos.domain.model.Folder
import com.lifeos.domain.repository.FolderRepository
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow

class GetFoldersUseCase(
    private val folderRepository: FolderRepository
) {
    operator fun invoke(userId: String): Flow<Result<List<Folder>>> {
        return folderRepository.getFolders(userId)
    }
}
