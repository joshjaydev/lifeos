package com.lifeos.app.ui.screens.atman

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifeos.domain.model.Action
import com.lifeos.domain.model.AiSuggestion
import com.lifeos.domain.model.ChatMessage
import com.lifeos.domain.model.Event
import com.lifeos.domain.model.Goal
import com.lifeos.domain.model.Note
import com.lifeos.domain.model.Principle
import com.lifeos.domain.model.TimeBlock
import com.lifeos.domain.repository.*
import com.lifeos.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import javax.inject.Inject

@HiltViewModel
class AtmanViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val aiSuggestionRepository: AiSuggestionRepository,
    private val actionRepository: ActionRepository,
    private val eventRepository: EventRepository,
    private val noteRepository: NoteRepository,
    private val goalRepository: GoalRepository,
    private val principleRepository: PrincipleRepository,
    private val timeBlockRepository: TimeBlockRepository
) : ViewModel() {

    private val userId = "user_1" // Hardcoded for now

    val messages: StateFlow<List<ChatMessage>> = chatRepository.getChatMessages(userId)
        .map { result ->
            if (result is Result.Success) result.data else emptyList()
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Pending suggestions that haven't been acted upon
    val pendingSuggestions: StateFlow<List<AiSuggestion>> = aiSuggestionRepository.getPendingSuggestions(userId)
        .map { result ->
            if (result is Result.Success) result.data else emptyList()
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun sendMessage(text: String) {
        if (text.isBlank()) return

        viewModelScope.launch {
            val userMessage = ChatMessage(
                id = java.util.UUID.randomUUID().toString(),
                userId = userId,
                role = com.lifeos.domain.model.MessageRole.USER,
                content = text,
                createdAt = Clock.System.now()
            )
            
            // 1. Save user message
            chatRepository.saveChatMessage(userMessage)

            // TODO: Implement actual AI response generation here.
            // For now, the user message is saved to the local DB.
        }
    }

    fun acceptSuggestion(suggestion: AiSuggestion) {
        viewModelScope.launch {
            aiSuggestionRepository.updateSuggestionStatus(suggestion.id, com.lifeos.domain.model.SuggestionStatus.APPROVED)
        }
    }

    fun declineSuggestion(suggestion: AiSuggestion) {
        viewModelScope.launch {
            aiSuggestionRepository.updateSuggestionStatus(suggestion.id, com.lifeos.domain.model.SuggestionStatus.REJECTED)
        }
    }
    
    fun clearHistory() {
        viewModelScope.launch {
            chatRepository.clearChatHistory(userId)
        }
    }
}
