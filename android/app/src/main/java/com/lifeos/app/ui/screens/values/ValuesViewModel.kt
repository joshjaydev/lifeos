package com.lifeos.app.ui.screens.values

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifeos.domain.model.Goal
import com.lifeos.domain.model.Principle
import com.lifeos.domain.repository.GoalRepository
import com.lifeos.domain.repository.PrincipleRepository
import com.lifeos.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ValuesViewModel @Inject constructor(
    private val principleRepository: PrincipleRepository,
    private val goalRepository: GoalRepository
) : ViewModel() {

    // Hardcoded for now
    private val userId = "user_1"

    val principles: StateFlow<List<Principle>> = principleRepository.getPrinciples(userId).map { result ->
        when (result) {
            is Result.Success -> result.data
            else -> emptyList()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val goals: StateFlow<List<Goal>> = goalRepository.getGoals(userId).map { result ->
        when (result) {
            is Result.Success -> result.data
            else -> emptyList()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun createPrinciple(principle: Principle) {
        viewModelScope.launch {
            principleRepository.createPrinciple(principle.copy(userId = userId))
        }
    }

    fun updatePrinciple(principle: Principle) {
        viewModelScope.launch {
            principleRepository.updatePrinciple(principle)
        }
    }

    fun deletePrinciple(id: String) {
        viewModelScope.launch {
            principleRepository.deletePrinciple(id)
        }
    }

    fun createGoal(goal: Goal) {
        viewModelScope.launch {
            goalRepository.createGoal(goal.copy(userId = userId))
            // TODO: Trigger automatic folder and notebook creation here or in UseCase
        }
    }

    fun updateGoal(goal: Goal) {
        viewModelScope.launch {
            goalRepository.updateGoal(goal)
        }
    }

    fun deleteGoal(id: String) {
        viewModelScope.launch {
            goalRepository.deleteGoal(id)
        }
    }
}
