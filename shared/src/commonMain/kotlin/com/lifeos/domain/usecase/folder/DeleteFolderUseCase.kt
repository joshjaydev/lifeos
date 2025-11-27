package com.lifeos.domain.usecase.folder

import com.lifeos.domain.repository.FolderRepository
import com.lifeos.domain.util.Result

class DeleteFolderUseCase(
    private val folderRepository: FolderRepository
) {
    suspend operator fun invoke(folderId: String): Result<Unit> {
        return folderRepository.deleteFolder(folderId)
    }
}
