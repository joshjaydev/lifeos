package com.lifeos.app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DragIndicator
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.lifeos.domain.model.Action
import com.lifeos.domain.model.Event
import com.lifeos.util.toAmPmString
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

// UI Model for a time slot
data class DayBlock(
    val id: String,
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val actionId: String? = null
)

// Global drag state
object DragDropState {
    private var _isDragging = mutableStateOf(false)
    private var _draggedAction = mutableStateOf<Action?>(null)
    private var _dragOffset = mutableStateOf(Offset.Zero)
    private var _dropTargets = mutableStateMapOf<String, DropTargetInfo>()

    val isDragging: State<Boolean> = _isDragging
    val draggedAction: State<Action?> = _draggedAction
    val dragOffset: State<Offset> = _dragOffset

    data class DropTargetInfo(
        val bounds: Rect,
        val onDrop: (Action) -> Unit
    )

    fun startDrag(action: Action, offset: Offset) {
        _draggedAction.value = action
        _dragOffset.value = offset
        _isDragging.value = true
    }

    fun updateDragOffset(offset: Offset) {
        _dragOffset.value = offset
    }

    fun endDrag() {
        val action = _draggedAction.value
        val currentOffset = _dragOffset.value
        
        if (action != null) {
            // Find which drop target we're over
            _dropTargets.values.firstOrNull { targetInfo ->
                targetInfo.bounds.contains(currentOffset)
            }?.onDrop?.invoke(action)
        }
        
        _isDragging.value = false
        _draggedAction.value = null
        _dragOffset.value = Offset.Zero
    }

    fun registerDropTarget(id: String, bounds: Rect, onDrop: (Action) -> Unit) {
        _dropTargets[id] = DropTargetInfo(bounds, onDrop)
    }

    fun unregisterDropTarget(id: String) {
        _dropTargets.remove(id)
    }

    fun isOverDropTarget(targetId: String): Boolean {
        val targetInfo = _dropTargets[targetId] ?: return false
        return targetInfo.bounds.contains(_dragOffset.value)
    }
}

@Composable
fun CustomDragDropActionItem(
    action: Action,
    onActionClick: () -> Unit,
    modifier: Modifier = Modifier,
    folderColor: Long? = null
) {
    var expanded by remember { mutableStateOf(false) }
    var dragStarted by remember { mutableStateOf(false) }
    var globalPosition by remember { mutableStateOf(Offset.Zero) }
    var dragHandlePosition by remember { mutableStateOf(Offset.Zero) }

    val backgroundColor = folderColor?.let { Color(it.toInt()) } ?: MaterialTheme.colorScheme.surfaceVariant
    // Simple text color logic
    val contentColor = if ((0.299 * backgroundColor.red + 0.587 * backgroundColor.green + 0.114 * backgroundColor.blue) > 0.5) Color.Black else Color.White

    Card(
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                globalPosition = coordinates.positionInWindow()
            }
            .alpha(if (DragDropState.isDragging.value && DragDropState.draggedAction.value == action) 0.5f else 1f)
            .clickable {
                if (!dragStarted) {
                    expanded = !expanded
                    onActionClick()
                }
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .height(IntrinsicSize.Min)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = action.title,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        color = contentColor,
                        maxLines = if (expanded) Int.MAX_VALUE else 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    if (action.isRecurring) {
                        Text(
                            text = "🔄",
                            style = MaterialTheme.typography.bodySmall,
                            color = contentColor
                        )
                    }
                }

                if (expanded) {
                    Spacer(modifier = Modifier.height(8.dp))
                    // action description not in model currently, skipping
                    /*
                    Text(
                        text = action.description ?: "No description available",
                        style = MaterialTheme.typography.bodyMedium,
                        color = contentColor.copy(alpha = 0.8f)
                    )
                    */
                }

                action.dueDate?.let { dueDate ->
                    val localDateTime = dueDate.toLocalDateTime(TimeZone.currentSystemDefault())
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    val timeStr = if (action.dueTime != null) {
                        action.dueTime!!.toAmPmString()
                    } else {
                         val hour = if (localDateTime.hour == 0) 12 else if (localDateTime.hour > 12) localDateTime.hour - 12 else localDateTime.hour
                        val amPm = if (localDateTime.hour < 12) "am" else "pm"
                        "${hour}:${localDateTime.minute.toString().padStart(2, '0')}$amPm"
                    }
                   
                    Text(
                        text = "Due: ${localDateTime.date} @ $timeStr",
                        style = MaterialTheme.typography.bodySmall,
                        color = contentColor
                    )
                }
            }
            
            // Drag handle icon - centered vertically for entire action item
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.DragIndicator,
                    contentDescription = "Drag to move action",
                    tint = contentColor,
                    modifier = Modifier
                        .size(28.dp)
                        .onGloballyPositioned { coordinates ->
                            dragHandlePosition = coordinates.positionInWindow()
                        }
                        .pointerInput(action.id) {
                            detectDragGestures(
                                onDragStart = { offset ->
                                    dragStarted = true
                                    // Use the actual drag handle position plus the drag offset
                                    val globalDragStart = dragHandlePosition + offset
                                    DragDropState.startDrag(action, globalDragStart)
                                },
                                onDragEnd = {
                                    if (dragStarted) {
                                        DragDropState.endDrag()
                                        dragStarted = false
                                    }
                                },
                                onDragCancel = {
                                    if (dragStarted) {
                                        DragDropState.endDrag()
                                        dragStarted = false
                                    }
                                }
                            ) { _, dragAmount ->
                                if (dragStarted) {
                                    DragDropState.updateDragOffset(DragDropState.dragOffset.value + dragAmount)
                                }
                            }
                        }
                )
            }
        }
    }
}

@Composable
fun CustomDropTargetDayBlock(
    dayBlock: DayBlock,
    assignedAction: Action?,
    events: List<Event> = emptyList(),
    onActionDropped: (String, String) -> Unit, // actionId, dayBlockId
    onActionAssigned: (String, String) -> Unit, // can be same as dropped or removal
    onDeleteEvent: (Event) -> Unit = {},
    modifier: Modifier = Modifier,
    actionFolderColor: Long? = null
) {
    val isHovered by remember {
        derivedStateOf {
            DragDropState.isDragging.value && DragDropState.isOverDropTarget(dayBlock.id)
        }
    }

    DisposableEffect(dayBlock.id) {
        onDispose {
            DragDropState.unregisterDropTarget(dayBlock.id)
        }
    }
    
    val backgroundColor = when {
        isHovered -> MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f)
        assignedAction != null -> actionFolderColor?.let { Color(it.toInt()) } ?: MaterialTheme.colorScheme.primary
        events.isNotEmpty() -> MaterialTheme.colorScheme.primaryContainer // Purple for events
        else -> MaterialTheme.colorScheme.surfaceVariant
    }
    
    // Simple text color logic
    val contentColor = if ((0.299 * backgroundColor.red + 0.587 * backgroundColor.green + 0.114 * backgroundColor.blue) > 0.5) Color.Black else Color.White


    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .onGloballyPositioned { coordinates ->
                DragDropState.registerDropTarget(
                    dayBlock.id,
                    coordinates.boundsInWindow()
                ) { droppedAction ->
                    onActionDropped(droppedAction.id, dayBlock.id)
                }
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.width(80.dp)
            ) {
                Text(
                    text = dayBlock.startTime.toAmPmString(),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium,
                    color = contentColor
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                when {
                    assignedAction != null -> {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = assignedAction.title,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Medium,
                                    color = contentColor,
                                    modifier = Modifier.weight(1f),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                
                                TextButton(
                                    onClick = { onActionAssigned("", dayBlock.id) }, // Empty action ID implies removal
                                    modifier = Modifier.size(24.dp),
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Text(
                                        text = "✕",
                                        fontSize = 14.sp,
                                        color = contentColor
                                    )
                                }
                            }
                            // Show events below action
                            events.take(1).forEach { event ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = event.title,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = contentColor.copy(alpha = 0.8f),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                    }
                    events.isNotEmpty() -> {
                        Column {
                            events.take(2).forEach { event ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = event.title,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = contentColor,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                            if (events.size > 2) {
                                Text(
                                    text = "+${events.size - 2} more",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = contentColor.copy(alpha = 0.6f)
                                )
                            }
                        }
                    }
                    else -> {
                        Text(
                            text = if (isHovered) "Drop Action Here" else "No Action",
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (isHovered) 
                                MaterialTheme.colorScheme.primary 
                            else 
                                contentColor.copy(alpha = 0.5f),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DragOverlay() {
    val isDragging by DragDropState.isDragging
    val draggedAction by DragDropState.draggedAction
    val dragOffset by DragDropState.dragOffset

    if (isDragging && draggedAction != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1000f)
        ) {
            Card(
                modifier = Modifier
                    .offset(
                        x = with(LocalDensity.current) { (dragOffset.x).toDp() }, // Adjusted offset logic if needed
                        y = with(LocalDensity.current) { (dragOffset.y).toDp() }
                    )
                    .shadow(8.dp)
                    .alpha(0.9f),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Text(
                    text = draggedAction!!.title,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
