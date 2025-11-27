package com.lifeos.app.ui.screens.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifeos.domain.model.Note
import com.lifeos.domain.model.Notebook
import com.lifeos.domain.repository.NoteRepository
import com.lifeos.domain.repository.NotebookRepository
import com.lifeos.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import javax.inject.Inject

data class NotesUiState(
    val notebooks: List<Notebook> = emptyList(),
    val notes: List<Note> = emptyList(),
    val selectedNotebookId: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val notebookRepository: NotebookRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotesUiState())
    val uiState: StateFlow<NotesUiState> = _uiState.asStateFlow()

    // Hardcoded user ID for now - will be replaced with auth later
    private val currentUserId = "user_1"

    private var hasInitialized = false

    init {
        loadNotebooks()
        loadNotes()
    }

    private fun loadNotebooks() {
        viewModelScope.launch {
            notebookRepository.getNotebooks(currentUserId).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.update { it.copy(notebooks = result.data, isLoading = false) }

                        // Create default notebook if none exist (only once)
                        if (!hasInitialized && result.data.isEmpty()) {
                            hasInitialized = true
                            val defaultNotebook = Notebook(
                                id = java.util.UUID.randomUUID().toString(),
                                userId = currentUserId,
                                name = "General",
                                isDefault = true,
                                goalId = null,
                                createdAt = Clock.System.now(),
                                updatedAt = Clock.System.now()
                            )
                            createNotebook(defaultNotebook)
                        }
                    }
                    is Result.Error -> {
                        _uiState.update {
                            it.copy(
                                error = "Failed to load notebooks: ${result.exception.message}",
                                isLoading = false
                            )
                        }
                    }
                    is Result.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    private fun loadNotes() {
        viewModelScope.launch {
            noteRepository.getNotes(currentUserId).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.update { it.copy(notes = result.data, isLoading = false) }
                    }
                    is Result.Error -> {
                        _uiState.update {
                            it.copy(
                                error = result.message ?: "Failed to load notes",
                                isLoading = false
                            )
                        }
                    }
                    is Result.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    fun selectNotebook(notebookId: String?) {
        _uiState.update { it.copy(selectedNotebookId = notebookId) }

        if (notebookId != null) {
            viewModelScope.launch {
                noteRepository.getNotesByNotebook(notebookId).collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _uiState.update { it.copy(notes = result.data, isLoading = false) }
                        }
                        is Result.Error -> {
                            _uiState.update {
                                it.copy(
                                    error = result.message ?: "Failed to load notes",
                                    isLoading = false
                                )
                            }
                        }
                        is Result.Loading -> {
                            _uiState.update { it.copy(isLoading = true) }
                        }
                    }
                }
            }
        } else {
            loadNotes()
        }
    }

    fun createNotebook(notebook: Notebook) {
        viewModelScope.launch {
            when (val result = notebookRepository.createNotebook(notebook.copy(userId = currentUserId))) {
                is Result.Success -> {
                    // Notebook will be automatically reloaded via Flow
                }
                is Result.Error -> {
                    _uiState.update {
                        it.copy(error = result.message ?: "Failed to create notebook")
                    }
                }
                is Result.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun updateNotebook(notebook: Notebook) {
        viewModelScope.launch {
            when (val result = notebookRepository.updateNotebook(notebook)) {
                is Result.Success -> {
                    // Notebook will be automatically reloaded via Flow
                }
                is Result.Error -> {
                    _uiState.update {
                        it.copy(error = result.message ?: "Failed to update notebook")
                    }
                }
                is Result.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun deleteNotebook(notebookId: String) {
        viewModelScope.launch {
            when (val result = notebookRepository.deleteNotebook(notebookId)) {
                is Result.Success -> {
                    // Notebook will be automatically reloaded via Flow
                    // Also clear selection if deleted notebook was selected
                    if (_uiState.value.selectedNotebookId == notebookId) {
                        selectNotebook(null)
                    }
                }
                is Result.Error -> {
                    _uiState.update {
                        it.copy(error = result.message ?: "Failed to delete notebook")
                    }
                }
                is Result.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun createNote(note: Note) {
        viewModelScope.launch {
            when (val result = noteRepository.createNote(note.copy(userId = currentUserId))) {
                is Result.Success -> {
                    // Note will be automatically reloaded via Flow
                }
                is Result.Error -> {
                    _uiState.update {
                        it.copy(error = result.message ?: "Failed to create note")
                    }
                }
                is Result.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            when (val result = noteRepository.updateNote(note)) {
                is Result.Success -> {
                    // Note will be automatically reloaded via Flow
                }
                is Result.Error -> {
                    _uiState.update {
                        it.copy(error = result.message ?: "Failed to update note")
                    }
                }
                is Result.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun deleteNote(noteId: String) {
        viewModelScope.launch {
            when (val result = noteRepository.deleteNote(noteId)) {
                is Result.Success -> {
                    // Note will be automatically reloaded via Flow
                }
                is Result.Error -> {
                    _uiState.update {
                        it.copy(error = result.message ?: "Failed to delete note")
                    }
                }
                is Result.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    fun getDefaultOrFirstNotebookId(): String? {
        val notebooks = _uiState.value.notebooks
        return notebooks.find { it.isDefault }?.id ?: notebooks.firstOrNull()?.id
    }
}

