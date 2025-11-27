package com.lifeos.app.ui.screens.actions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Repeat
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.foundation.clickable // Added
import com.lifeos.app.ui.theme.Green500
import com.lifeos.domain.model.Action
import com.lifeos.domain.model.Folder
import com.lifeos.domain.model.RecurrenceType
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn // Added import
import kotlinx.datetime.toJavaLocalDate // Added
import java.time.format.DateTimeFormatter // Added
import com.lifeos.util.toAmPmString
import com.lifeos.app.ui.components.CustomDatePickerDialog
import com.lifeos.app.ui.components.CustomTimePickerDialog
import com.lifeos.app.ui.components.CustomNotificationPickerDialog

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ActionDialog(
    action: Action? = null,
    folders: List<Folder>,
    onDismiss: () -> Unit,
    onSave: (Action) -> Unit,
    onDelete: (() -> Unit)? = null
) {
    var title by remember { mutableStateOf(action?.title ?: "") }
    var selectedFolderId by remember { mutableStateOf(action?.folderId ?: folders.firstOrNull { it.isDefault }?.id ?: folders.firstOrNull()?.id ?: "") }
    var dueDate by remember { mutableStateOf(action?.dueDate) }
    var dueTime by remember { mutableStateOf<LocalTime?>(action?.dueTime) }
    var isRecurring by remember { mutableStateOf(action?.isRecurring ?: false) }
    var recurrenceType by remember { mutableStateOf(action?.recurrenceType) }
    var notifyDays by remember { mutableStateOf((action?.notifyBefore ?: 0) / 1440) }
    var notifyHours by remember { mutableStateOf(((action?.notifyBefore ?: 0) % 1440) / 60) }
    var notifyMinutes by remember { mutableStateOf((action?.notifyBefore ?: 0) % 60) }
    
    // Dialog States
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    var showFolderPicker by remember { mutableStateOf(false) }
    var showRecurrencePicker by remember { mutableStateOf(false) }
    var showNotificationPicker by remember { mutableStateOf(false) }
    var showDeleteConfirmation by remember { mutableStateOf(false) }

    val isEditing = action != null
    val selectedFolder = folders.find { it.id == selectedFolderId }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(28.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            tonalElevation = 6.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Header: Title Input
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = { Text("What needs to be done?", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)) },
                    textStyle = MaterialTheme.typography.headlineSmall,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Quick Chips Row
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Folder Chip
                    AssistChip(
                        onClick = { showFolderPicker = true },
                        label = { Text(selectedFolder?.name ?: "Select Folder") },
                        leadingIcon = {
                             Icon(
                                 imageVector = Icons.Outlined.Folder,
                                 contentDescription = null,
                                 tint = selectedFolder?.color?.let { Color(it) } ?: MaterialTheme.colorScheme.primary
                             )
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            labelColor = MaterialTheme.colorScheme.onSurface
                        )
                    )

                    // Date Chip
                    AssistChip(
                        onClick = { showDatePicker = true },
                        label = { Text(dueDate?.let { formatDateShort(it) } ?: "Set Date") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.CalendarMonth,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                                tint = if (dueDate != null) Green500 else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = if (dueDate != null) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface
                        )
                    )
                    
                    // Time Chip (only if date is set)
                     if (dueDate != null) {
                        AssistChip(
                            onClick = { showTimePicker = true },
                            label = { Text(dueTime?.toAmPmString() ?: "Set Time") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Schedule,
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp),
                                    tint = if (dueTime != null) Green500 else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            },
                             colors = AssistChipDefaults.assistChipColors(
                                containerColor = if (dueTime != null) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                Spacer(modifier = Modifier.height(16.dp))

                // Detailed Settings List
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    
                    // Recurrence
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { showRecurrencePicker = !showRecurrencePicker }
                            .padding(vertical = 12.dp, horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Repeat,
                            contentDescription = null,
                            tint = if (isRecurring) Green500 else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Recurring",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            if (isRecurring) {
                                Text(
                                    text = recurrenceType?.name?.lowercase()?.replaceFirstChar { it.uppercase() } ?: "Daily",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Green500
                                )
                            }
                        }
                        Switch(
                            checked = isRecurring,
                            onCheckedChange = { isRecurring = it }
                        )
                    }
                    
                    if (isRecurring && showRecurrencePicker) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(start = 40.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                             RecurrenceType.entries.forEach { type ->
                                FilterChip(
                                    selected = recurrenceType == type,
                                    onClick = { recurrenceType = type },
                                    label = { Text(type.name.lowercase().replaceFirstChar { it.uppercase() }) }
                                )
                             }
                        }
                    }

                    // Notification
                     Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { showNotificationPicker = true }
                            .padding(vertical = 12.dp, horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = null,
                            tint = if (notifyDays > 0 || notifyHours > 0 || notifyMinutes > 0) Green500 else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                         Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Remind me",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            val durationText = formatDuration(notifyDays, notifyHours, notifyMinutes)
                            if (durationText != "No notification") {
                                Text(
                                    text = "$durationText before",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Green500
                                )
                            }
                        }
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isEditing && onDelete != null) {
                         IconButton(
                            onClick = { showDeleteConfirmation = true },
                            colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.error)
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    } else {
                        Spacer(modifier = Modifier.size(48.dp)) // Placeholder to balance layout
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        TextButton(onClick = onDismiss) {
                            Text("Cancel")
                        }
                        Button(
                            onClick = {
                                val now = Clock.System.now()
                                val totalNotifyMinutes = (notifyDays * 1440) + (notifyHours * 60) + notifyMinutes
                                val actionToSave = Action(
                                    id = action?.id ?: "",
                                    userId = action?.userId ?: "",
                                    folderId = selectedFolderId,
                                    title = title,
                                    dueDate = dueDate,
                                    dueTime = dueTime,
                                    isRecurring = isRecurring,
                                    recurrenceType = if (isRecurring) recurrenceType else null,
                                    notifyBefore = totalNotifyMinutes,
                                    createdAt = action?.createdAt ?: now,
                                    updatedAt = now
                                )
                                onSave(actionToSave)
                                onDismiss()
                            },
                            enabled = title.isNotBlank() && selectedFolderId.isNotBlank(),
                            colors = ButtonDefaults.buttonColors(containerColor = Green500)
                        ) {
                            Text(if (isEditing) "Save" else "Create")
                        }
                    }
                }
            }
        }
    }
    
    // ---- Sub-Dialogs ----

    // Folder Picker Dialog
    if (showFolderPicker) {
        AlertDialog(
            onDismissRequest = { showFolderPicker = false },
            title = { Text("Select Folder") },
            text = {
                 Column {
                     folders.forEach { folder ->
                         Row(
                             modifier = Modifier
                                 .fillMaxWidth()
                                 .clickable {
                                     selectedFolderId = folder.id
                                     showFolderPicker = false
                                 }
                                 .padding(12.dp),
                             verticalAlignment = Alignment.CenterVertically
                         ) {
                             Icon(
                                 imageVector = Icons.Outlined.Folder,
                                 contentDescription = null,
                                 tint = folder.color?.let { Color(it) } ?: MaterialTheme.colorScheme.primary
                             )
                             Spacer(modifier = Modifier.width(16.dp))
                             Text(folder.name)
                             if (folder.id == selectedFolderId) {
                                 Spacer(modifier = Modifier.weight(1f))
                                 Icon(Icons.Default.Check, contentDescription = null, tint = Green500)
                             }
                         }
                     }
                 }
            },
            confirmButton = {
                 TextButton(onClick = { showFolderPicker = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Notification Picker Dialog
    if (showNotificationPicker) {
        CustomNotificationPickerDialog(
            currentDays = notifyDays,
            currentHours = notifyHours,
            currentMinutes = notifyMinutes,
            onNotificationSelected = { days, hours, minutes ->
                notifyDays = days
                notifyHours = hours
                notifyMinutes = minutes
            },
            onDismiss = { showNotificationPicker = false }
        )
    }

    // Date picker dialog
    if (showDatePicker) {
        CustomDatePickerDialog(
            initialDate = dueDate?.toLocalDateTime(TimeZone.currentSystemDefault())?.date ?: Clock.System.todayIn(TimeZone.currentSystemDefault()),
            onDateSelected = { date ->
                // Convert LocalDate back to Instant at start of day in system default zone
                val instant = date.atStartOfDayIn(TimeZone.currentSystemDefault())
                dueDate = instant
                showDatePicker = false
            },
            onDismiss = { showDatePicker = false }
        )
    }

    // Time picker dialog
    if (showTimePicker) {
        CustomTimePickerDialog(
            initialTime = dueTime,
            onTimeSelected = { time: LocalTime ->
                dueTime = time
                showTimePicker = false
            },
            onDismiss = { showTimePicker = false }
        )
    }

    // Delete confirmation dialog
    if (showDeleteConfirmation && onDelete != null) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            title = { Text("Delete Action") },
            text = { Text("Are you sure you want to delete this action? This action cannot be undone.") },
            confirmButton = {
                Button(
                    onClick = {
                        onDelete()
                        showDeleteConfirmation = false
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirmation = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

private fun formatDateShort(instant: Instant): String {
    val localDate = instant.toLocalDateTime(TimeZone.currentSystemDefault()).date
    val javaDate = localDate.toJavaLocalDate()
    val formatter = DateTimeFormatter.ofPattern("MMM d")
    return javaDate.format(formatter)
}

private fun formatDuration(days: Int, hours: Int, minutes: Int): String {
    val parts = mutableListOf<String>()
    if (days > 0) parts.add("${days}d")
    if (hours > 0) parts.add("${hours}h")
    if (minutes > 0) parts.add("${minutes}m")
    return if (parts.isEmpty()) "No notification" else parts.joinToString(" ")
}