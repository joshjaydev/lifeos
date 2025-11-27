package com.lifeos.domain.usecase.action

import com.lifeos.domain.model.Action
import com.lifeos.domain.repository.ActionRepository
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow

class GetActionsByFolderUseCase(
    private val actionRepository: ActionRepository
) {
    operator fun invoke(folderId: String): Flow<Result<List<Action>>> {
        return actionRepository.getActionsByFolder(folderId)
    }
}
