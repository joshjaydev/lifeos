package com.lifeos.app.ui.screens.time

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifeos.domain.model.Action
import com.lifeos.domain.model.Event
import com.lifeos.domain.model.TimeBlock
import com.lifeos.domain.repository.EventRepository
import com.lifeos.domain.repository.TimeBlockRepository
import com.lifeos.domain.usecase.action.CompleteActionUseCase
import com.lifeos.domain.usecase.action.GetActionsUseCase
import com.lifeos.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.*
import javax.inject.Inject

import com.lifeos.domain.model.Folder
import com.lifeos.domain.usecase.folder.GetFoldersUseCase

// DataStore extension
private val Context.timeDataStore: DataStore<Preferences> by preferencesDataStore(name = "time_preferences")

@HiltViewModel
class TimeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getActionsUseCase: GetActionsUseCase,
    private val getFoldersUseCase: GetFoldersUseCase,
    private val completeActionUseCase: CompleteActionUseCase,
    private val timeBlockRepository: TimeBlockRepository,
    private val eventRepository: EventRepository
) : ViewModel() {

    companion object {
        private val CALENDAR_VIEW_MODE_KEY = stringPreferencesKey("calendar_view_mode")
        private val BLOCK_SIZE_KEY = intPreferencesKey("block_size")
    }

    private val _selectedDate = MutableStateFlow(Clock.System.todayIn(TimeZone.currentSystemDefault()))
    val selectedDate: StateFlow<LocalDate> = _selectedDate.asStateFlow()

    private val _blockSize = MutableStateFlow(15)
    val blockSize: StateFlow<Int> = _blockSize.asStateFlow()

    private val _calendarViewMode = MutableStateFlow("Month")
    val calendarViewMode: StateFlow<String> = _calendarViewMode.asStateFlow()

    init {
        // Load persisted preferences
        viewModelScope.launch {
            context.timeDataStore.data.collect { preferences ->
                preferences[CALENDAR_VIEW_MODE_KEY]?.let { mode ->
                    _calendarViewMode.value = mode
                }
                preferences[BLOCK_SIZE_KEY]?.let { size ->
                    _blockSize.value = size
                }
            }
        }
    }

    // Planner: Actions for the selected date
    // Note: This is a simplification. Real app might need 'userId' from Auth.
    // Assuming single user or userId handling in repo for now.
    // Repo methods require userId. We need a way to get current userId.
    // For now, I'll hardcode a userId or check if there's a SessionManager.
    
    private val userId = "user_1" // TODO: Inject UserSession

    val folders: StateFlow<List<Folder>> = getFoldersUseCase(userId).map { result ->
        if (result is Result.Success) result.data else emptyList()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val plannerActions: StateFlow<List<Action>> = _selectedDate.flatMapLatest { date ->
        getActionsUseCase(userId).map { result ->
            when (result) {
                is Result.Success -> result.data.filter { 
                    it.dueDate?.toLocalDateTime(TimeZone.currentSystemDefault())?.date == date 
                }
                is Result.Error -> emptyList()
                is Result.Loading -> emptyList()
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Expose all actions for infinite scrolling views
    val allActions: StateFlow<List<Action>> = getActionsUseCase(userId).map { result ->
        if (result is Result.Success) result.data else emptyList()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Block: Actions to be blocked (e.g. no due date or today)
    val blockActions: StateFlow<List<Action>> = getActionsUseCase(userId).map { result ->
        if (result is Result.Success) result.data else emptyList()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Block: TimeBlocks for the selected date (for Block view)
    val timeBlocks: StateFlow<List<TimeBlock>> = _selectedDate.flatMapLatest { date ->
        timeBlockRepository.getTimeBlocks(userId, date).map { result ->
            when (result) {
                is Result.Success -> result.data
                is Result.Error -> emptyList()
                is Result.Loading -> emptyList()
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // All TimeBlocks for calendar views (shows multiple days)
    val allTimeBlocks: StateFlow<List<TimeBlock>> = timeBlockRepository.getAllTimeBlocks(userId).map { result ->
        when (result) {
            is Result.Success -> result.data
            is Result.Error -> emptyList()
            is Result.Loading -> emptyList()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Calendar: Events
    val events: StateFlow<List<Event>> = eventRepository.getEvents(userId).map { result ->
        when (result) {
            is Result.Success -> result.data
            is Result.Error -> emptyList()
            is Result.Loading -> emptyList()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun onDateSelected(date: LocalDate) {
        _selectedDate.value = date
    }

    fun onBlockSizeChanged(size: Int) {
        _blockSize.value = size
        viewModelScope.launch {
            context.timeDataStore.edit { preferences ->
                preferences[BLOCK_SIZE_KEY] = size
            }
        }
    }

    fun onCalendarViewModeChanged(mode: String) {
        _calendarViewMode.value = mode
        viewModelScope.launch {
            context.timeDataStore.edit { preferences ->
                preferences[CALENDAR_VIEW_MODE_KEY] = mode
            }
        }
    }

    fun onActionCompleted(action: Action) {
        viewModelScope.launch {
            completeActionUseCase(action.id)
        }
    }
    
    fun onTimeBlockCreated(timeBlock: TimeBlock) {
        viewModelScope.launch {
            timeBlockRepository.createTimeBlock(timeBlock)
        }
    }

    fun onTimeBlockDeleted(timeBlockId: String) {
        viewModelScope.launch {
            timeBlockRepository.deleteTimeBlock(timeBlockId)
        }
    }

    fun onEventCreated(event: Event) {
        viewModelScope.launch {
            eventRepository.createEvent(event)
        }
    }

    fun onEventUpdated(event: Event) {
        viewModelScope.launch {
            when (val result = eventRepository.updateEvent(event)) {
                is Result.Success -> {} // Event will be reloaded via Flow
                is Result.Error -> { /* TODO: Handle error: _uiState.update { it.copy(error = result.message) } */ }
                is Result.Loading -> { /* TODO: Show loading state if needed */ }
            }
        }
    }

    fun onEventDeleted(eventId: String) {
        viewModelScope.launch {
            when (val result = eventRepository.deleteEvent(eventId)) {
                is Result.Success -> {} // Event will be reloaded via Flow
                is Result.Error -> { /* TODO: Handle error: _uiState.update { it.copy(error = result.message) } */ }
                is Result.Loading -> { /* TODO: Show loading state if needed */ }
            }
        }
    }
}
