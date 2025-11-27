package com.lifeos.app.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.lifeos.app.ui.theme.Green500
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTimePickerDialog(
    initialTime: LocalTime?,
    onTimeSelected: (LocalTime) -> Unit,
    onDismiss: () -> Unit
) {
    val timePickerState = rememberTimePickerState(
        initialHour = initialTime?.hour ?: 0,
        initialMinute = initialTime?.minute ?: 0,
        is24Hour = false
    )

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
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Custom Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Select Time",
                        style = MaterialTheme.typography.titleLarge
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                TimePicker(
                    state = timePickerState,
                    colors = TimePickerDefaults.colors(
                        clockDialColor = MaterialTheme.colorScheme.surface,
                        clockDialSelectedContentColor = Color.White,
                        clockDialUnselectedContentColor = MaterialTheme.colorScheme.onSurface,
                        selectorColor = Green500,
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        periodSelectorBorderColor = Green500,
                        periodSelectorSelectedContainerColor = Green500,
                        periodSelectorUnselectedContainerColor = MaterialTheme.colorScheme.surface,
                        periodSelectorSelectedContentColor = Color.White,
                        periodSelectorUnselectedContentColor = MaterialTheme.colorScheme.onSurface,
                        timeSelectorSelectedContainerColor = Green500,
                        timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.surface,
                        timeSelectorSelectedContentColor = Color.White,
                        timeSelectorUnselectedContentColor = MaterialTheme.colorScheme.onSurface
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            val selectedTime = LocalTime(timePickerState.hour, timePickerState.minute)
                            onTimeSelected(selectedTime)
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Green500)
                    ) {
                        Text("OK")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePickerDialog(
    initialDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialDate.atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds()
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        colors = DatePickerDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        confirmButton = {
            Button(
                onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        onDateSelected(Instant.fromEpochMilliseconds(millis).toLocalDateTime(TimeZone.UTC).date)
                    }
                    onDismiss()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Green500)
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                headlineContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                weekdayContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                subheadContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                yearContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                currentYearContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                selectedYearContentColor = Color.White,
                selectedYearContainerColor = Green500,
                dayContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                selectedDayContentColor = Color.White,
                selectedDayContainerColor = Green500,
                todayContentColor = Green500,
                todayDateBorderColor = Green500
            )
        )
    }
}

@Composable
fun CustomBlockSizeDialog(
    currentSize: Int,
    onSizeSelected: (Int) -> Unit,
    onDismiss: () -> Unit
) {
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
                modifier = Modifier.padding(24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Block Size",
                        style = MaterialTheme.typography.titleLarge
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Warning: Changing the block size may hide currently blocked actions that don't align with new time slots.",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(listOf(5, 10, 15, 30, 60)) { size ->
                        val label = if (size >= 60) "1 hr" else "$size min"
                        val isSelected = size == currentSize
                        
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onSizeSelected(size)
                                    onDismiss()
                                }
                                .padding(vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = { 
                                    onSizeSelected(size)
                                    onDismiss()
                                },
                                colors = RadioButtonDefaults.colors(selectedColor = Green500)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = label,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomNotificationPickerDialog(
    currentDays: Int,
    currentHours: Int,
    currentMinutes: Int,
    onNotificationSelected: (days: Int, hours: Int, minutes: Int) -> Unit,
    onDismiss: () -> Unit
) {
    var selectedDays by remember { mutableStateOf(currentDays) }
    var selectedHours by remember { mutableStateOf(currentHours) }
    var selectedMinutes by remember { mutableStateOf(currentMinutes) }

    // Preset options
    data class NotifyOption(val label: String, val days: Int, val hours: Int, val minutes: Int)
    val presets = listOf(
        NotifyOption("No reminder", 0, 0, 0),
        NotifyOption("5 minutes before", 0, 0, 5),
        NotifyOption("15 minutes before", 0, 0, 15),
        NotifyOption("30 minutes before", 0, 0, 30),
        NotifyOption("1 hour before", 0, 1, 0),
        NotifyOption("2 hours before", 0, 2, 0),
        NotifyOption("1 day before", 1, 0, 0),
        NotifyOption("Custom", -1, -1, -1)
    )

    var showCustom by remember { 
        mutableStateOf(
            presets.none { it.days == currentDays && it.hours == currentHours && it.minutes == currentMinutes } &&
            !(currentDays == 0 && currentHours == 0 && currentMinutes == 0)
        ) 
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .heightIn(max = 500.dp),
            shape = RoundedCornerShape(28.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            tonalElevation = 6.dp
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                // Header
                Text(
                    text = "Remind me",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Scrollable preset options
                LazyColumn(
                    modifier = Modifier.weight(1f, fill = false),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(presets) { option ->
                        val isSelected = if (option.days == -1) {
                            showCustom
                        } else {
                            !showCustom && option.days == selectedDays && option.hours == selectedHours && option.minutes == selectedMinutes
                        }

                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    if (option.days == -1) {
                                        showCustom = true
                                    } else {
                                        showCustom = false
                                        selectedDays = option.days
                                        selectedHours = option.hours
                                        selectedMinutes = option.minutes
                                    }
                                },
                            shape = RoundedCornerShape(12.dp),
                            color = if (isSelected) Green500 else Color.Transparent
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = option.label,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }

                // Custom input section
                if (showCustom) {
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Days
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Days",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            OutlinedTextField(
                                value = if (selectedDays == 0) "" else selectedDays.toString(),
                                onValueChange = { selectedDays = it.toIntOrNull() ?: 0 },
                                singleLine = true,
                                placeholder = { Text("0") },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Green500,
                                    cursorColor = Green500
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        
                        // Hours
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Hours",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            OutlinedTextField(
                                value = if (selectedHours == 0) "" else selectedHours.toString(),
                                onValueChange = { selectedHours = it.toIntOrNull() ?: 0 },
                                singleLine = true,
                                placeholder = { Text("0") },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Green500,
                                    cursorColor = Green500
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        
                        // Minutes
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Min",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            OutlinedTextField(
                                value = if (selectedMinutes == 0) "" else selectedMinutes.toString(),
                                onValueChange = { selectedMinutes = it.toIntOrNull() ?: 0 },
                                singleLine = true,
                                placeholder = { Text("0") },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Green500,
                                    cursorColor = Green500
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Action buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            onNotificationSelected(selectedDays, selectedHours, selectedMinutes)
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Green500)
                    ) {
                        Text("Done")
                    }
                }
            }
        }
    }
}

@Composable
fun CustomViewModeDialog(
    currentMode: String,
    onModeSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    // Full screen overlay with sliding drawer from left
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
                .clickable(onClick = onDismiss)
        ) {
            // Sliding drawer from left
            Surface(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(260.dp)
                    .clickable(enabled = false) { },
                shape = RoundedCornerShape(topEnd = 28.dp, bottomEnd = 28.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                tonalElevation = 6.dp
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Header
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        Text(
                            text = "View",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // View mode options
                    listOf("Day", "3 Day", "Week", "Month").forEach { mode ->
                        val isSelected = mode == currentMode
                        
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                                .clickable {
                                    onModeSelected(mode)
                                    onDismiss()
                                },
                            shape = RoundedCornerShape(16.dp),
                            color = if (isSelected) Green500 else Color.Transparent
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = mode,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.weight(1f))
                    
                    // Footer hint
                    Text(
                        text = "Tap outside to close",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}