package com.lifeos.domain.usecase.action

import com.lifeos.domain.model.Action
import com.lifeos.domain.repository.ActionRepository
import com.lifeos.domain.util.Result
import kotlinx.coroutines.flow.Flow

class GetActionsUseCase(
    private val actionRepository: ActionRepository
) {
    operator fun invoke(userId: String): Flow<Result<List<Action>>> {
        return actionRepository.getActions(userId)
    }
}
