package com.lifeos.data.local

import com.lifeos.domain.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

/**
 * Interface for LocalDatabase.
 * Platform-specific implementations provide the actual database access.
 * This allows the shared code to work with any database implementation.
 */
interface LocalDatabase {
    // Actions
    fun getActions(userId: String): Flow<List<Action>>
    fun getActionsByFolder(folderId: String): Flow<List<Action>>
    fun getActionById(id: String): Flow<Action?>
    suspend fun insertAction(action: Action)
    suspend fun updateAction(action: Action)
    suspend fun deleteAction(id: String)

    // Action Completions
    suspend fun insertActionCompletion(completion: ActionCompletion)
    fun getActionCompletions(actionId: String): Flow<List<ActionCompletion>>

    // Folders
    fun getFolders(userId: String): Flow<List<Folder>>
    fun getFolderById(id: String): Flow<Folder?>
    suspend fun insertFolder(folder: Folder)
    suspend fun updateFolder(folder: Folder)
    suspend fun deleteFolder(id: String)

    // Notes
    fun getNotes(userId: String): Flow<List<Note>>
    fun getNotesByNotebook(notebookId: String): Flow<List<Note>>
    fun getNoteById(id: String): Flow<Note?>
    fun getJournalNote(notebookId: String, date: LocalDate): Flow<Note?>
    suspend fun insertNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(id: String)

    // Notebooks
    fun getNotebooks(userId: String): Flow<List<Notebook>>
    fun getNotebookById(id: String): Flow<Notebook?>
    suspend fun insertNotebook(notebook: Notebook)
    suspend fun updateNotebook(notebook: Notebook)
    suspend fun deleteNotebook(id: String)

    // Goals
    fun getGoals(userId: String): Flow<List<Goal>>
    fun getGoalById(id: String): Flow<Goal?>
    suspend fun insertGoal(goal: Goal)
    suspend fun updateGoal(goal: Goal)
    suspend fun deleteGoal(id: String)

    // Principles
    fun getPrinciples(userId: String): Flow<List<Principle>>
    fun getSubPrinciples(parentId: String): Flow<List<Principle>>
    fun getPrincipleById(id: String): Flow<Principle?>
    suspend fun insertPrinciple(principle: Principle)
    suspend fun updatePrinciple(principle: Principle)
    suspend fun deletePrinciple(id: String)

    // TimeBlocks
    fun getTimeBlocks(userId: String, date: LocalDate): Flow<List<TimeBlock>>
    fun getAllTimeBlocks(userId: String): Flow<List<TimeBlock>>
    fun getTimeBlockById(id: String): Flow<TimeBlock?>
    suspend fun insertTimeBlock(timeBlock: TimeBlock)
    suspend fun updateTimeBlock(timeBlock: TimeBlock)
    suspend fun deleteTimeBlock(id: String)

    // Events
    fun getEvents(userId: String): Flow<List<Event>>
    fun getEventsBetween(userId: String, startTime: Instant, endTime: Instant): Flow<List<Event>>
    fun getEventById(id: String): Flow<Event?>
    suspend fun insertEvent(event: Event)
    suspend fun updateEvent(event: Event)
    suspend fun deleteEvent(id: String)

    // Chat Messages
    fun getChatMessages(userId: String): Flow<List<ChatMessage>>
    fun getChatMessageById(id: String): Flow<ChatMessage?>
    suspend fun insertChatMessage(message: ChatMessage)
    suspend fun deleteChatMessage(id: String)
    suspend fun clearChatHistory(userId: String)

    // AI Suggestions
    fun getSuggestions(userId: String): Flow<List<AiSuggestion>>
    fun getPendingSuggestions(userId: String): Flow<List<AiSuggestion>>
    fun getSuggestionById(id: String): Flow<AiSuggestion?>
    suspend fun insertSuggestion(suggestion: AiSuggestion)
    suspend fun updateSuggestion(suggestion: AiSuggestion)
    suspend fun deleteSuggestion(id: String)

    // Profile
    fun getProfile(userId: String): Flow<Profile?>
    suspend fun insertProfile(profile: Profile)
    suspend fun updateProfile(profile: Profile)
    suspend fun deleteProfile(userId: String)
}
