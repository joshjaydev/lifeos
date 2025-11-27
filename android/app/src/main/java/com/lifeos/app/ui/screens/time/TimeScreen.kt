package com.lifeos.app.ui.screens.time

import android.view.View
import androidx.compose.foundation.clickable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.CalendarMonth // Added
import androidx.compose.material.icons.outlined.Schedule // Added
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifeos.app.ui.components.CustomDragDropActionItem
import com.lifeos.app.ui.components.CustomDropTargetDayBlock
import com.lifeos.app.ui.components.CustomFilterChip
import com.lifeos.app.ui.components.CustomFilledIconButton
import com.lifeos.app.ui.components.CustomDatePickerDialog
import com.lifeos.app.ui.components.CustomBlockSizeDialog // Added
import com.lifeos.app.ui.components.CustomViewModeDialog // Added
import com.lifeos.app.ui.components.DayBlock
import com.lifeos.app.ui.components.DragOverlay
import com.lifeos.app.ui.theme.Green500
import com.lifeos.domain.model.Action
import com.lifeos.domain.model.Event
import com.lifeos.domain.model.Folder
import com.lifeos.domain.model.TimeBlock
import com.lifeos.util.toAmPmString
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import kotlinx.datetime.toJavaLocalDate // Added import
import java.time.format.DateTimeFormatter // Added import

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeScreen(
    viewModel: TimeViewModel = hiltViewModel()
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Planner", "Block", "Calendar")
    val selectedDate by viewModel.selectedDate.collectAsState()
    val blockSize by viewModel.blockSize.collectAsState()
    
    val allActions by viewModel.allActions.collectAsState()
    val blockActions by viewModel.blockActions.collectAsState()
    val timeBlocks by viewModel.timeBlocks.collectAsState()
    val allTimeBlocks by viewModel.allTimeBlocks.collectAsState()
    val events by viewModel.events.collectAsState()
    val calendarViewMode by viewModel.calendarViewMode.collectAsState()
    val folders by viewModel.folders.collectAsState()

    var showEventDialog by remember { mutableStateOf(false) }
    var editingEvent by remember { mutableStateOf<Event?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onSurface
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = {
                            Text(
                                text = title,
                                fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (selectedTab) {
                0 -> PlannerContent(
                    date = selectedDate,
                    actions = allActions,
                    folders = viewModel.folders,
                    onComplete = viewModel::onActionCompleted
                )
                1 -> BlockContent(
                    date = selectedDate,
                    blockSize = blockSize,
                    actions = blockActions,
                    timeBlocks = timeBlocks,
                    events = events,
                    folders = viewModel.folders,
                    onTimeBlockCreated = viewModel::onTimeBlockCreated,
                    onTimeBlockDeleted = viewModel::onTimeBlockDeleted,
                    onDateSelected = viewModel::onDateSelected,
                    onBlockSizeChanged = viewModel::onBlockSizeChanged
                )
                2 -> CalendarContent(
                    date = selectedDate,
                    blockSize = blockSize,
                    viewMode = calendarViewMode,
                    onViewModeChanged = viewModel::onCalendarViewModeChanged,
                    events = events,
                    timeBlocks = allTimeBlocks,
                    actions = allActions,
                    folders = folders,
                    onDateSelected = viewModel::onDateSelected,
                    onAddEvent = {
                        editingEvent = null
                        showEventDialog = true
                    },
                    onEventClick = { event ->
                        editingEvent = event
                        showEventDialog = true
                    }
                )
            }
        }
        
        // Drag Overlay for global drag representation
        DragOverlay()
    }

    // Event Dialog
    if (showEventDialog) {
        EventDialog(
            event = editingEvent,
            onDismiss = {
                showEventDialog = false
                editingEvent = null
            },
            onSave = { event ->
                if (editingEvent != null) {
                    viewModel.onEventUpdated(event)
                } else {
                    viewModel.onEventCreated(event)
                }
            },
            onDelete = editingEvent?.let {
                { viewModel.onEventDeleted(it.id) }
            }
        )
    }
}

@Composable
private fun PlannerContent(
    date: LocalDate,
    actions: List<Action>,
    folders: StateFlow<List<Folder>>,
    onComplete: (Action) -> Unit
) {
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = 10000)
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val colWidth = screenWidth / 3

    val firstVisibleIndex by remember { derivedStateOf { listState.firstVisibleItemIndex } }
    val currentVisibleDate = date.plus(DatePeriod(days = firstVisibleIndex - 10000))
    val monthTitle = "${currentVisibleDate.month.name.lowercase().replaceFirstChar { it.uppercase() }} ${currentVisibleDate.year}"

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = monthTitle,
            style = MaterialTheme.typography.headlineSmall,
            color = Color(0xFFB0B0B0),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyRow(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            items(count = 20000) { index ->
                val dayOffset = index - 10000
                val currentDay = date.plus(DatePeriod(days = dayOffset))
                
                val dayActions = actions.filter { 
                     it.dueDate?.toLocalDateTime(TimeZone.currentSystemDefault())?.date == currentDay
                }

                Column(
                    modifier = Modifier
                        .width(colWidth)
                        .fillMaxHeight()
                        .padding(horizontal = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = currentDay.dayOfWeek.name.take(3),
                        style = MaterialTheme.typography.labelMedium,
                        color = if (currentDay == date) Green500 else MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = currentDay.dayOfMonth.toString(),
                        style = MaterialTheme.typography.titleLarge
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(dayActions) { action ->
                            val folderColor = folders.collectAsState().value.find { it.id == action.folderId }?.color
                            PlannerActionItem(
                                action = action,
                                folderColor = folderColor,
                                onComplete = { onComplete(action) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PlannerActionItem(
    action: Action,
    folderColor: Long?,
    onComplete: () -> Unit
) {
    var isChecked by remember { mutableStateOf(false) }
    
    val alpha by animateFloatAsState(
        targetValue = if (isChecked) 0f else 1f,
        animationSpec = tween(durationMillis = 600),
        label = "alpha"
    )
    val scale by animateFloatAsState(
        targetValue = if (isChecked) 0.8f else 1f,
        animationSpec = tween(durationMillis = 600),
        label = "scale"
    )

    LaunchedEffect(isChecked) {
        if (isChecked) {
            kotlinx.coroutines.delay(600)
            onComplete()
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                this.alpha = alpha
                this.scaleX = scale
                this.scaleY = scale
            },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = folderColor?.let { Color(it.toInt()) } ?: Green500,
                    uncheckedColor = folderColor?.let { Color(it.toInt()) } ?: Green500
                )
            )
            Text(
                text = action.title,
                style = MaterialTheme.typography.titleMedium,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                color = Color.White
            )
            action.dueTime?.let {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = it.toAmPmString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun BlockContent(
    date: LocalDate,
    blockSize: Int,
    actions: List<Action>,
    timeBlocks: List<TimeBlock>,
    events: List<Event>,
    folders: StateFlow<List<Folder>>,
    onTimeBlockCreated: (TimeBlock) -> Unit,
    onTimeBlockDeleted: (String) -> Unit,
    onDateSelected: (LocalDate) -> Unit,
    onBlockSizeChanged: (Int) -> Unit
) {
    val listState = rememberLazyListState()
    var showDatePicker by remember { mutableStateOf(false) }
    var showBlockSizeSheet by remember { mutableStateOf(false) }

    // Scroll to current time on initial load (current time at top of screen)
    LaunchedEffect(blockSize) {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time
        val currentMinutes = now.hour * 60 + now.minute
        val currentSlotIndex = currentMinutes / blockSize
        listState.animateScrollToItem(currentSlotIndex)
    }

    if (showDatePicker) {
        CustomDatePickerDialog(
            initialDate = date,
            onDateSelected = onDateSelected,
            onDismiss = { showDatePicker = false }
        )
    }

    if (showBlockSizeSheet) {
        CustomBlockSizeDialog(
            currentSize = blockSize,
            onSizeSelected = { size ->
                onBlockSizeChanged(size)
                showBlockSizeSheet = false
            },
            onDismiss = { showBlockSizeSheet = false }
        )
    }

    Column {
        // Action blocks to drag
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(0.45f)
        ) {
            items(actions) { action ->
                val folderColor = folders.collectAsState().value.find { it.id == action.folderId }?.color
                CustomDragDropActionItem(
                    action = action,
                    onActionClick = { }, // No specific click action for now
                    folderColor = folderColor
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Date and time selector
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomFilterChip(
                label = formatMonthDay(date), // Block: Month Day
                onClick = { showDatePicker = true },
                leadingIcon = Icons.Outlined.CalendarMonth,
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.width(8.dp))

            CustomFilterChip(
                label = if (blockSize >= 60) "${blockSize / 60} hr" else "$blockSize min",
                onClick = { showBlockSizeSheet = true },
                leadingIcon = Icons.Outlined.Schedule,
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Time slots
        LazyColumn(
            state = listState,
            modifier = Modifier.weight(0.55f)
        ) {
            // Slots covering full 24 hours based on block size
            val slotsPerHour = 60 / blockSize
            items(count = 24 * slotsPerHour) { index ->
                val startMinute = index * blockSize
                val startHour = (startMinute / 60)
                val startMin = startMinute % 60
                val endMinute = startMinute + blockSize
                val endHour = (endMinute / 60) % 24
                val endMin = endMinute % 60

                val startTime = LocalTime(startHour, startMin)
                val endTime = LocalTime(endHour, endMin)
                
                val block = timeBlocks.find { it.startTime == startTime }
                val blockAction = if (block != null) {
                    actions.find { it.id == block.actionId }
                } else null
                
                val blockActionFolderColor = blockAction?.folderId?.let { folderId ->
                    folders.collectAsState().value.find { it.id == folderId }?.color
                }

                // Create a transient DayBlock for the UI component
                val dayBlock = DayBlock(
                    id = "${date}-${startTime}",
                    date = date,
                    startTime = startTime,
                    endTime = endTime,
                    actionId = blockAction?.id
                )

                // Filter events that overlap with this time slot
                val slotEvents = events.filter { event ->
                    event.startDatetime.toLocalDateTime(TimeZone.currentSystemDefault()).date == date &&
                    event.startDatetime.toLocalDateTime(TimeZone.currentSystemDefault()).time < endTime &&
                    event.endDatetime.toLocalDateTime(TimeZone.currentSystemDefault()).time > startTime
                }

                CustomDropTargetDayBlock(
                    dayBlock = dayBlock,
                    assignedAction = blockAction,
                    events = slotEvents,
                    actionFolderColor = blockActionFolderColor,
                    onActionDropped = { actionId, _ -> 
                        val droppedAction = actions.find { it.id == actionId }
                        if (droppedAction != null) {
                            if (block != null) {
                                onTimeBlockDeleted(block.id)
                            }
                            
                            val newBlock = TimeBlock(
                                id = java.util.UUID.randomUUID().toString(),
                                userId = droppedAction.userId,
                                actionId = droppedAction.id,
                                blockDate = date,
                                startTime = startTime,
                                endTime = endTime,
                                createdAt = Clock.System.now()
                            )
                            onTimeBlockCreated(newBlock)
                        }
                    },
                    onActionAssigned = { _, _ ->
                        if (block != null) {
                            onTimeBlockDeleted(block.id)
                        }
                    }
                )
                HorizontalDivider()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CalendarContent(
    date: LocalDate,
    blockSize: Int,
    viewMode: String,
    onViewModeChanged: (String) -> Unit,
    events: List<Event>,
    timeBlocks: List<TimeBlock>,
    actions: List<Action>,
    folders: List<Folder>,
    onDateSelected: (LocalDate) -> Unit,
    onAddEvent: () -> Unit,
    onEventClick: (Event) -> Unit
) {
    var showViewMenu by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    if (showDatePicker) {
        CustomDatePickerDialog(
            initialDate = date,
            onDateSelected = onDateSelected,
            onDismiss = { showDatePicker = false }
        )
    }
    if (showViewMenu) {
        CustomViewModeDialog(
            currentMode = viewMode,
            onModeSelected = { mode ->
                onViewModeChanged(mode)
                showViewMenu = false
            },
            onDismiss = { showViewMenu = false }
        )
    }
    
    Column {
        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Hamburger Menu
            CustomFilledIconButton(onClick = { showViewMenu = true }, icon = Icons.Default.Menu)

            // Date Selector (centered)
            CustomFilterChip(
                label = formatMonthYear(date), // Calendar: Month Year
                onClick = { showDatePicker = true },
                leadingIcon = Icons.Outlined.CalendarMonth,
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            // Add Event Button
            CustomFilledIconButton(onClick = onAddEvent, icon = Icons.Default.Add)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // View Content
        Box(modifier = Modifier.weight(1f)) {
            when (viewMode) {
                "Month" -> MonthViewContent(
                    selectedDate = date,
                    events = events,
                    timeBlocks = timeBlocks,
                    actions = actions,
                    folders = folders,
                    onDateClick = onDateSelected
                )
                "Week" -> MultiDayView(
                    startDate = date.plus(DatePeriod(days = -date.dayOfWeek.ordinal)), // Start of week (Mon)
                    dayCount = 7,
                    blockSize = blockSize,
                    events = events,
                    timeBlocks = timeBlocks,
                    actions = actions,
                    folders = folders,
                    onEventClick = onEventClick,
                    onTimeBlockClick = {}
                )
                "3 Day" -> MultiDayView(
                    startDate = date,
                    dayCount = 3,
                    blockSize = blockSize,
                    events = events,
                    timeBlocks = timeBlocks,
                    actions = actions,
                    folders = folders,
                    onEventClick = onEventClick,
                    onTimeBlockClick = {}
                )
                "Day" -> MultiDayView(
                    startDate = date,
                    dayCount = 1,
                    blockSize = blockSize,
                    events = events,
                    timeBlocks = timeBlocks,
                    actions = actions,
                    folders = folders,
                    onEventClick = onEventClick,
                    onTimeBlockClick = {}
                )
            }
        }
    }
}

// Helper functions for date formatting
private fun formatMonthDay(localDate: LocalDate): String {
    val javaDate = localDate.toJavaLocalDate()
    val formatter = DateTimeFormatter.ofPattern("MMMM d")
    return javaDate.format(formatter)
}

private fun formatMonthYear(localDate: LocalDate): String {
    val javaDate = localDate.toJavaLocalDate()
    val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
    return javaDate.format(formatter)
}
