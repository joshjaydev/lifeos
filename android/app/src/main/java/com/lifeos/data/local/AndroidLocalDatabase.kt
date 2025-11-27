package com.lifeos.data.local

import com.lifeos.app.data.local.LifeOSDatabase
import com.lifeos.app.data.mapper.*
import com.lifeos.domain.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import javax.inject.Inject

class AndroidLocalDatabase @Inject constructor(
    private val database: LifeOSDatabase
) : LocalDatabase {

    // Actions
    override fun getActions(userId: String): Flow<List<Action>> {
        return database.actionDao().getActions(userId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getActionsByFolder(folderId: String): Flow<List<Action>> {
        return database.actionDao().getActionsByFolder(folderId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getActionById(id: String): Flow<Action?> {
        return database.actionDao().getAction(id).map { it?.toDomain() }
    }

    override suspend fun insertAction(action: Action) {
        val entity = action.toEntity()
        val finalEntity = if (entity.id.isEmpty()) {
            entity.copy(id = java.util.UUID.randomUUID().toString())
        } else {
            entity
        }
        database.actionDao().insert(finalEntity)
    }

    override suspend fun updateAction(action: Action) {
        database.actionDao().update(action.toEntity())
    }

    override suspend fun deleteAction(id: String) {
        database.actionDao().delete(id)
    }

    // Action Completions
    override suspend fun insertActionCompletion(completion: ActionCompletion) {
        val entity = completion.toEntity()
        val finalEntity = if (entity.id.isEmpty()) {
            entity.copy(id = java.util.UUID.randomUUID().toString())
        } else {
            entity
        }
        database.actionCompletionDao().insert(finalEntity)
    }

    override fun getActionCompletions(actionId: String): Flow<List<ActionCompletion>> {
        return database.actionCompletionDao().getCompletions(actionId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    // Folders
    override fun getFolders(userId: String): Flow<List<Folder>> {
        return database.folderDao().getFolders(userId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getFolderById(id: String): Flow<Folder?> {
        return database.folderDao().getFolder(id).map { it?.toDomain() }
    }

    override suspend fun insertFolder(folder: Folder) {
        val entity = folder.toEntity()
        val finalEntity = if (entity.id.isEmpty()) {
            entity.copy(id = java.util.UUID.randomUUID().toString())
        } else {
            entity
        }
        database.folderDao().insert(finalEntity)
    }

    override suspend fun updateFolder(folder: Folder) {
        database.folderDao().update(folder.toEntity())
    }

    override suspend fun deleteFolder(id: String) {
        database.actionDao().deleteByFolder(id)
        database.folderDao().delete(id)
    }

    // Notes
    override fun getNotes(userId: String): Flow<List<Note>> {
        return database.noteDao().getNotes(userId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getNotesByNotebook(notebookId: String): Flow<List<Note>> {
        return database.noteDao().getNotesByNotebook(notebookId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getNoteById(id: String): Flow<Note?> {
        return database.noteDao().getNote(id).map { it?.toDomain() }
    }

    override fun getJournalNote(notebookId: String, date: LocalDate): Flow<Note?> {
        return database.noteDao().getJournalNote(notebookId, date.toString()).map { it?.toDomain() }
    }

    override suspend fun insertNote(note: Note) {
        database.noteDao().insert(note.toEntity())
    }

    override suspend fun updateNote(note: Note) {
        database.noteDao().update(note.toEntity())
    }

    override suspend fun deleteNote(id: String) {
        database.noteDao().delete(id)
    }

    // Notebooks
    override fun getNotebooks(userId: String): Flow<List<Notebook>> {
        return database.notebookDao().getNotebooks(userId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getNotebookById(id: String): Flow<Notebook?> {
        return database.notebookDao().getNotebook(id).map { it?.toDomain() }
    }

    override suspend fun insertNotebook(notebook: Notebook) {
        database.notebookDao().insert(notebook.toEntity())
    }

    override suspend fun updateNotebook(notebook: Notebook) {
        database.notebookDao().update(notebook.toEntity())
    }

    override suspend fun deleteNotebook(id: String) {
        database.notebookDao().delete(id)
    }

    // Goals
    override fun getGoals(userId: String): Flow<List<Goal>> {
        return database.goalDao().getGoals(userId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getGoalById(id: String): Flow<Goal?> {
        return database.goalDao().getGoal(id).map { it?.toDomain() }
    }

    override suspend fun insertGoal(goal: Goal) {
        database.goalDao().insert(goal.toEntity())
    }

    override suspend fun updateGoal(goal: Goal) {
        database.goalDao().update(goal.toEntity())
    }

    override suspend fun deleteGoal(id: String) {
        database.goalDao().delete(id)
    }

    // Principles
    override fun getPrinciples(userId: String): Flow<List<Principle>> {
        return database.principleDao().getPrinciples(userId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getSubPrinciples(parentId: String): Flow<List<Principle>> {
        return database.principleDao().getSubPrinciples(parentId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getPrincipleById(id: String): Flow<Principle?> {
        return database.principleDao().getPrinciple(id).map { it?.toDomain() }
    }

    override suspend fun insertPrinciple(principle: Principle) {
        database.principleDao().insert(principle.toEntity())
    }

    override suspend fun updatePrinciple(principle: Principle) {
        database.principleDao().update(principle.toEntity())
    }

    override suspend fun deletePrinciple(id: String) {
        database.principleDao().delete(id)
    }

    // TimeBlocks
    override fun getTimeBlocks(userId: String, date: LocalDate): Flow<List<TimeBlock>> {
        return database.timeBlockDao().getTimeBlocks(userId, date.toString()).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getAllTimeBlocks(userId: String): Flow<List<TimeBlock>> {
        return database.timeBlockDao().getAllTimeBlocks(userId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getTimeBlockById(id: String): Flow<TimeBlock?> {
        return database.timeBlockDao().getTimeBlock(id).map { it?.toDomain() }
    }

    override suspend fun insertTimeBlock(timeBlock: TimeBlock) {
        database.timeBlockDao().insert(timeBlock.toEntity())
    }

    override suspend fun updateTimeBlock(timeBlock: TimeBlock) {
        database.timeBlockDao().update(timeBlock.toEntity())
    }

    override suspend fun deleteTimeBlock(id: String) {
        database.timeBlockDao().delete(id)
    }

    // Events
    override fun getEvents(userId: String): Flow<List<Event>> {
        return database.eventDao().getEvents(userId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getEventsBetween(userId: String, startTime: Instant, endTime: Instant): Flow<List<Event>> {
        return database.eventDao().getEventsBetween(userId, startTime.toEpochMilliseconds(), endTime.toEpochMilliseconds()).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getEventById(id: String): Flow<Event?> {
        return database.eventDao().getEvent(id).map { it?.toDomain() }
    }

    override suspend fun insertEvent(event: Event) {
        database.eventDao().insert(event.toEntity())
    }

    override suspend fun updateEvent(event: Event) {
        database.eventDao().update(event.toEntity())
    }

    override suspend fun deleteEvent(id: String) {
        database.eventDao().delete(id)
    }

    // Chat Messages
    override fun getChatMessages(userId: String): Flow<List<ChatMessage>> {
        return database.chatMessageDao().getMessages(userId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getChatMessageById(id: String): Flow<ChatMessage?> {
        return database.chatMessageDao().getMessage(id).map { it?.toDomain() }
    }

    override suspend fun insertChatMessage(message: ChatMessage) {
        database.chatMessageDao().insert(message.toEntity())
    }

    override suspend fun deleteChatMessage(id: String) {
        database.chatMessageDao().delete(id)
    }

    override suspend fun clearChatHistory(userId: String) {
        database.chatMessageDao().deleteAll(userId)
    }

    // AI Suggestions
    override fun getSuggestions(userId: String): Flow<List<AiSuggestion>> {
        return database.aiSuggestionDao().getSuggestions(userId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getPendingSuggestions(userId: String): Flow<List<AiSuggestion>> {
        return database.aiSuggestionDao().getPendingSuggestions(userId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getSuggestionById(id: String): Flow<AiSuggestion?> {
        return database.aiSuggestionDao().getSuggestion(id).map { it?.toDomain() }
    }

    override suspend fun insertSuggestion(suggestion: AiSuggestion) {
        database.aiSuggestionDao().insert(suggestion.toEntity())
    }

    override suspend fun updateSuggestion(suggestion: AiSuggestion) {
        database.aiSuggestionDao().update(suggestion.toEntity())
    }

    override suspend fun deleteSuggestion(id: String) {
        database.aiSuggestionDao().delete(id)
    }

    // Profile
    override fun getProfile(userId: String): Flow<Profile?> {
        return database.profileDao().getProfile(userId).map { it?.toDomain() }
    }

    override suspend fun insertProfile(profile: Profile) {
        database.profileDao().insert(profile.toEntity())
    }

    override suspend fun updateProfile(profile: Profile) {
        database.profileDao().update(profile.toEntity())
    }

    override suspend fun deleteProfile(userId: String) {
        // ProfileDao usually deletes by ID which is userId
        database.profileDao().delete(userId)
    }
}
