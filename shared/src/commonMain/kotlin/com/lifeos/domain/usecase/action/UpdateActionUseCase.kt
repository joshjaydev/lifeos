package com.lifeos.domain.usecase.action

import com.lifeos.domain.model.Action
import com.lifeos.domain.repository.ActionRepository
import com.lifeos.domain.util.Result

class UpdateActionUseCase(
    private val actionRepository: ActionRepository
) {
    suspend operator fun invoke(action: Action): Result<Unit> {
        return actionRepository.updateAction(action)
    }
}
