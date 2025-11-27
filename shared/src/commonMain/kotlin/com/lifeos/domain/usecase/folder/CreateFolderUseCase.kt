package com.lifeos.domain.usecase.folder

import com.lifeos.domain.model.Folder
import com.lifeos.domain.repository.FolderRepository
import com.lifeos.domain.util.Result

class CreateFolderUseCase(
    private val folderRepository: FolderRepository
) {
    suspend operator fun invoke(folder: Folder): Result<Unit> {
        return folderRepository.createFolder(folder)
    }
}
