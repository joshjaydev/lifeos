package com.lifeos.domain.usecase.action

import com.lifeos.domain.repository.ActionRepository
import com.lifeos.domain.util.Result

class DeleteActionUseCase(
    private val actionRepository: ActionRepository
) {
    suspend operator fun invoke(actionId: String): Result<Unit> {
        return actionRepository.deleteAction(actionId)
    }
}
