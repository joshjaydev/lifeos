package com.lifeos.app.ui.screens.time

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.CalendarMonth
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
import androidx.compose.foundation.clickable
import com.lifeos.app.ui.theme.Green500
import com.lifeos.domain.model.Event
import com.lifeos.domain.model.RecurrenceType
import com.lifeos.util.toAmPmString
import kotlinx.datetime.*
import java.time.format.DateTimeFormatter
import java.util.UUID
import kotlinx.datetime.toJavaLocalDate
import kotlin.time.Duration.Companion.hours
import com.lifeos.app.ui.components.CustomDatePickerDialog
import com.lifeos.app.ui.components.CustomTimePickerDialog
import com.lifeos.app.ui.components.CustomNotificationPickerDialog
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalTime // Added import

// Helper function to round up to the nearest hour
private fun roundUpToNextHour(instant: Instant): Instant {
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    val nextHour = if (localDateTime.minute > 0 || localDateTime.second > 0) {
        LocalDateTime(
            localDateTime.date,
            LocalTime((localDateTime.hour + 1) % 24, 0)
        )
    } else {
        localDateTime
    }
    // If we rolled over to next day
    val finalDateTime = if (localDateTime.hour == 23 && nextHour.hour == 0) {
        LocalDateTime(
            localDateTime.date.plus(DatePeriod(days = 1)),
            LocalTime(0, 0)
        )
    } else {
        nextHour
    }
    return finalDateTime.toInstant(TimeZone.currentSystemDefault())
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun EventDialog(
    event: Event? = null,
    onDismiss: () -> Unit,
    onSave: (Event) -> Unit,
    onDelete: (() -> Unit)? = null
) {
    // Default times: round up to nearest hour
    val defaultStart = remember { roundUpToNextHour(Clock.System.now()) }
    val defaultEnd = remember { defaultStart.plus(1.hours) }
    
    var title by remember { mutableStateOf(event?.title ?: "") }
    var startInstant by remember { mutableStateOf(event?.startDatetime ?: defaultStart) }
    var endInstant by remember { mutableStateOf(event?.endDatetime ?: defaultEnd) }
    var isRecurring by remember { mutableStateOf(event?.isRecurring ?: false) }
    var recurrenceType by remember { mutableStateOf(event?.recurrenceType) }
    var notifyDays by remember { mutableStateOf((event?.notifyBefore ?: 0) / 1440) }
    var notifyHours by remember { mutableStateOf(((event?.notifyBefore ?: 0) % 1440) / 60) }
    var notifyMinutes by remember { mutableStateOf((event?.notifyBefore ?: 0) % 60) }

    var showStartDatePicker by remember { mutableStateOf(false) }
    var showStartTimePicker by remember { mutableStateOf(false) }
    var showEndDatePicker by remember { mutableStateOf(false) }
    var showEndTimePicker by remember { mutableStateOf(false) }
    var showRecurrencePicker by remember { mutableStateOf(false) }
    var showNotificationPicker by remember { mutableStateOf(false) }
    var showDeleteConfirmation by remember { mutableStateOf(false) }

    val isEditing = event != null

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
                // Title Input
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = { Text("Event Title", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)) },
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
                    // Start Date
                    AssistChip(
                        onClick = { showStartDatePicker = true },
                        label = { Text(formatDateShort(startInstant)) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.CalendarMonth,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                                tint = Green500
                            )
                        }
                    )

                    // Start Time
                    AssistChip(
                        onClick = { showStartTimePicker = true },
                        label = { Text(startInstant.toLocalDateTime(TimeZone.currentSystemDefault()).time.toAmPmString()) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Schedule,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                                tint = Green500
                            )
                        }
                    )

                    Text("→", modifier = Modifier.align(Alignment.CenterVertically))

                     // End Date
                     AssistChip(
                        onClick = { showEndDatePicker = true },
                        label = { Text(formatDateShort(endInstant)) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.CalendarMonth,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                                tint = Green500
                            )
                        }
                    )
                    
                     // End Time
                    AssistChip(
                        onClick = { showEndTimePicker = true },
                        label = { Text(endInstant.toLocalDateTime(TimeZone.currentSystemDefault()).time.toAmPmString()) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Schedule,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                                tint = Green500
                            )
                        }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                Spacer(modifier = Modifier.height(16.dp))

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
                        Spacer(modifier = Modifier.size(48.dp))
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        TextButton(onClick = onDismiss) {
                            Text("Cancel")
                        }
                        Button(
                            onClick = {
                                val now = Clock.System.now()
                                val totalNotifyMinutes = (notifyDays * 1440) + (notifyHours * 60) + notifyMinutes
                                val eventToSave = Event(
                                    id = event?.id?.takeIf { it.isNotBlank() } ?: UUID.randomUUID().toString(),
                                    userId = event?.userId?.takeIf { it.isNotBlank() } ?: "user_1", // TODO: Get from auth
                                    title = title,
                                    description = null,
                                    startDatetime = startInstant,
                                    endDatetime = endInstant,
                                    isRecurring = isRecurring,
                                    recurrenceType = if (isRecurring) recurrenceType else null,
                                    notifyBefore = totalNotifyMinutes,
                                    createdAt = event?.createdAt ?: now,
                                    updatedAt = now
                                )
                                onSave(eventToSave)
                                onDismiss()
                            },
                            enabled = title.isNotBlank(),
                            colors = ButtonDefaults.buttonColors(containerColor = Green500)
                        ) {
                            Text(if (isEditing) "Save" else "Create")
                        }
                    }
                }
            }
        }
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

    // Start Date Picker
    if (showStartDatePicker) {
        CustomDatePickerDialog(
            initialDate = startInstant.toLocalDateTime(TimeZone.currentSystemDefault()).date,
            onDateSelected = { date ->
                startInstant = combineDateWithTime(date, startInstant)
                showStartDatePicker = false
            },
            onDismiss = { showStartDatePicker = false }
        )
    }
    
    // Start Time Picker
    if (showStartTimePicker) {
        CustomTimePickerDialog(
            initialTime = startInstant.toLocalDateTime(TimeZone.currentSystemDefault()).time,
            onTimeSelected = { time: LocalTime ->
                startInstant = combineDateWithTime(startInstant, time)
                showStartTimePicker = false
            },
            onDismiss = { showStartTimePicker = false }
        )
    }

    // End Date Picker
    if (showEndDatePicker) {
        CustomDatePickerDialog(
            initialDate = endInstant.toLocalDateTime(TimeZone.currentSystemDefault()).date,
            onDateSelected = { date ->
                endInstant = combineDateWithTime(date, endInstant)
                showEndDatePicker = false
            },
            onDismiss = { showEndDatePicker = false }
        )
    }

    // End Time Picker
    if (showEndTimePicker) {
        CustomTimePickerDialog(
            initialTime = endInstant.toLocalDateTime(TimeZone.currentSystemDefault()).time,
            onTimeSelected = { time: LocalTime ->
                endInstant = combineDateWithTime(endInstant, time)
                showEndTimePicker = false
            },
            onDismiss = { showEndTimePicker = false }
        )
    }

    // Delete Confirmation
    if (showDeleteConfirmation && onDelete != null) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            title = { Text("Delete Event") },
            text = { Text("Are you sure you want to delete this event? This action cannot be undone.") },
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

private fun combineDateWithTime(date: LocalDate, originalInstant: Instant): Instant {
    val time = originalInstant.toLocalDateTime(TimeZone.currentSystemDefault()).time
    return LocalDateTime(date, time).toInstant(TimeZone.currentSystemDefault())
}

private fun combineDateWithTime(originalInstant: Instant, time: LocalTime): Instant {
    val date = originalInstant.toLocalDateTime(TimeZone.currentSystemDefault()).date
    return LocalDateTime(date, time).toInstant(TimeZone.currentSystemDefault())
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