package com.lifeos.app.ui.screens.actions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifeos.domain.model.Action
import com.lifeos.domain.model.Folder
import com.lifeos.domain.usecase.action.*
import com.lifeos.domain.usecase.folder.*
import com.lifeos.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import javax.inject.Inject

data class ActionsUiState(
    val folders: List<Folder> = emptyList(),
    val actions: List<Action> = emptyList(),
    val selectedFolderId: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class ActionsViewModel @Inject constructor(
    private val getActionsUseCase: GetActionsUseCase,
    private val getActionsByFolderUseCase: GetActionsByFolderUseCase,
    private val createActionUseCase: CreateActionUseCase,
    private val updateActionUseCase: UpdateActionUseCase,
    private val deleteActionUseCase: DeleteActionUseCase,
    private val completeActionUseCase: CompleteActionUseCase,
    private val getFoldersUseCase: GetFoldersUseCase,
    private val createFolderUseCase: CreateFolderUseCase,
    private val updateFolderUseCase: UpdateFolderUseCase,
    private val deleteFolderUseCase: DeleteFolderUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ActionsUiState())
    val uiState: StateFlow<ActionsUiState> = _uiState.asStateFlow()

    // Hardcoded user ID for now - will be replaced with auth later
    private val currentUserId = "user_1"

    private var hasInitialized = false

    init {
        loadFolders()
        loadActions()
    }

    private fun loadFolders() {
        viewModelScope.launch {
            getFoldersUseCase(currentUserId).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.update { it.copy(folders = result.data, isLoading = false) }

                        // Create default folder if none exist (only once)
                        if (!hasInitialized && result.data.isEmpty()) {
                            hasInitialized = true
                            val defaultFolder = Folder(
                                id = java.util.UUID.randomUUID().toString(),
                                userId = currentUserId,
                                name = "General",
                                color = 0xFF32CD33.toLong(), // LifeOS Green (Green500) as ARGB
                                isDefault = true,
                                goalId = null,
                                createdAt = Clock.System.now(),
                                updatedAt = Clock.System.now()
                            )
                            createFolder(defaultFolder)
                        }
                    }
                    is Result.Error -> {
                        _uiState.update {
                            it.copy(
                                error = "Failed to load folders: ${result.exception.message}",
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

    private fun loadActions() {
        viewModelScope.launch {
            getActionsUseCase(currentUserId).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.update { it.copy(actions = result.data, isLoading = false) }
                    }
                    is Result.Error -> {
                        _uiState.update {
                            it.copy(
                                error = result.message ?: "Failed to load actions",
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

    fun selectFolder(folderId: String?) {
        _uiState.update { it.copy(selectedFolderId = folderId) }

        if (folderId != null) {
            viewModelScope.launch {
                getActionsByFolderUseCase(folderId).collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _uiState.update { it.copy(actions = result.data, isLoading = false) }
                        }
                        is Result.Error -> {
                            _uiState.update {
                                it.copy(
                                    error = result.message ?: "Failed to load actions",
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
            loadActions()
        }
    }

    fun createAction(action: Action) {
        viewModelScope.launch {
            when (val result = createActionUseCase(action.copy(userId = currentUserId))) {
                is Result.Success -> {
                    // Action will be automatically reloaded via Flow
                }
                is Result.Error -> {
                    _uiState.update {
                        it.copy(error = result.message ?: "Failed to create action")
                    }
                }
                is Result.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun updateAction(action: Action) {
        viewModelScope.launch {
            when (val result = updateActionUseCase(action)) {
                is Result.Success -> {
                    // Action will be automatically reloaded via Flow
                }
                is Result.Error -> {
                    _uiState.update {
                        it.copy(error = result.message ?: "Failed to update action")
                    }
                }
                is Result.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun deleteAction(actionId: String) {
        viewModelScope.launch {
            when (val result = deleteActionUseCase(actionId)) {
                is Result.Success -> {
                    // Action will be automatically reloaded via Flow
                }
                is Result.Error -> {
                    _uiState.update {
                        it.copy(error = result.message ?: "Failed to delete action")
                    }
                }
                is Result.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun completeAction(actionId: String) {
        viewModelScope.launch {
            when (val result = completeActionUseCase(actionId)) {
                is Result.Success -> {
                    // Completion will be recorded
                }
                is Result.Error -> {
                    _uiState.update {
                        it.copy(error = result.message ?: "Failed to complete action")
                    }
                }
                is Result.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun createFolder(folder: Folder) {
        viewModelScope.launch {
            when (val result = createFolderUseCase(folder.copy(userId = currentUserId))) {
                is Result.Success -> {
                    // Folder will be automatically reloaded via Flow
                }
                is Result.Error -> {
                    _uiState.update {
                        it.copy(error = result.message ?: "Failed to create folder")
                    }
                }
                is Result.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun updateFolder(folder: Folder) {
        viewModelScope.launch {
            when (val result = updateFolderUseCase(folder)) {
                is Result.Success -> {
                    // Folder will be automatically reloaded via Flow
                }
                is Result.Error -> {
                    _uiState.update {
                        it.copy(error = result.message ?: "Failed to update folder")
                    }
                }
                is Result.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun deleteFolder(folderId: String) {
        viewModelScope.launch {
            when (val result = deleteFolderUseCase(folderId)) {
                is Result.Success -> {
                    // Folder will be automatically reloaded via Flow
                }
                is Result.Error -> {
                    _uiState.update {
                        it.copy(error = result.message ?: "Failed to delete folder")
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
}
