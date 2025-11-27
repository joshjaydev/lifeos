package com.lifeos.app.ui.screens.time

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lifeos.app.ui.theme.Green500
import com.lifeos.domain.model.Action
import com.lifeos.domain.model.Event
import com.lifeos.domain.model.Folder
import com.lifeos.domain.model.TimeBlock
import com.lifeos.util.toAmPmString
import kotlinx.datetime.*

@Composable
fun MultiDayView(
    startDate: LocalDate,
    dayCount: Int,
    blockSize: Int = 60,
    events: List<Event>,
    timeBlocks: List<TimeBlock>,
    actions: List<Action>,
    folders: List<Folder>,
    onEventClick: (Event) -> Unit,
    onTimeBlockClick: (TimeBlock) -> Unit
) {
    val scrollState = rememberScrollState()
    val density = LocalDensity.current
    
    // Calculate slot height based on block size
    // Each slot represents blockSize minutes, with comfortable spacing for labels
    val slotHeight = when (blockSize) {
        5 -> 24.dp
        10 -> 32.dp
        15 -> 40.dp
        30 -> 48.dp
        60 -> 64.dp
        else -> (blockSize * 1.5f).dp.coerceIn(24.dp, 80.dp)
    }
    // Total slots in a day
    val totalSlots = (24 * 60) / blockSize
    val totalHeight = slotHeight * totalSlots
    
    // Scroll to current time on initial load (current time at top of screen)
    LaunchedEffect(blockSize, density) {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time
        val currentMinutes = now.hour * 60 + now.minute
        val currentSlotIndex = currentMinutes / blockSize
        // Calculate scroll position in pixels using proper density
        val slotHeightPx = with(density) { slotHeight.toPx() }
        val targetScrollPosition = (currentSlotIndex * slotHeightPx).toInt()
        scrollState.animateScrollTo(targetScrollPosition)
    }
    
    Column(modifier = Modifier.fillMaxSize()) {
        // Header: Days
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 56.dp) // Space for time labels
        ) {
            repeat(dayCount) { i ->
                val date = startDate.plus(DatePeriod(days = i))
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = date.dayOfWeek.name.take(3),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = date.dayOfMonth.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = if (date == Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date) FontWeight.Bold else FontWeight.Normal,
                        color = if (date == Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        // Time Grid
        Box(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            Row(modifier = Modifier.height(totalHeight)) {
                // Time Labels Column - now based on block size
                Column(
                    modifier = Modifier
                        .width(56.dp)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.End
                ) {
                    repeat(totalSlots) { slotIndex ->
                        val totalMinutes = slotIndex * blockSize
                        val hour = totalMinutes / 60
                        val minute = totalMinutes % 60
                        val time = LocalTime(hour, minute)
                        
                        Box(
                            modifier = Modifier
                                .height(slotHeight)
                                .padding(end = 6.dp),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            Text(
                                text = time.toAmPmString(),
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = when {
                                    blockSize <= 10 -> 9.sp
                                    blockSize <= 15 -> 10.sp
                                    else -> 11.sp
                                },
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                // Days Columns
                Row(modifier = Modifier.fillMaxSize()) {
                    repeat(dayCount) { i ->
                        val date = startDate.plus(DatePeriod(days = i))
                        val dayEvents = events.filter { 
                            it.startDatetime.toLocalDateTime(TimeZone.currentSystemDefault()).date == date 
                        }
                        val dayBlocks = timeBlocks.filter { it.blockDate == date }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                        ) {
                            // pixels per minute = slot height / block size
                            val pixelsPerMinute = slotHeight / blockSize

                            // Render Events
                            dayEvents.forEach { event ->
                                val start = event.startDatetime.toLocalDateTime(TimeZone.currentSystemDefault()).time
                                val end = event.endDatetime.toLocalDateTime(TimeZone.currentSystemDefault()).time
                                val startMinutes = start.hour * 60 + start.minute
                                val endMinutes = if (end.hour == 0 && end.minute == 0 && startMinutes > 0) 24 * 60 else end.hour * 60 + end.minute
                                val durationMinutes = endMinutes - startMinutes
                                
                                val topOffset = pixelsPerMinute * startMinutes
                                val itemHeight = (pixelsPerMinute * durationMinutes).coerceAtLeast(slotHeight)

                                EventItem(
                                    event = event,
                                    itemHeight = itemHeight,
                                    modifier = Modifier
                                        .padding(1.dp)
                                        .fillMaxWidth()
                                        .offset(y = topOffset)
                                        .height(itemHeight),
                                    onClick = { onEventClick(event) }
                                )
                            }

                            // Render TimeBlocks
                            dayBlocks.forEach { block ->
                                val start = block.startTime
                                val end = block.endTime
                                val startMinutes = start.hour * 60 + start.minute
                                val endMinutes = if (end.hour == 0 && end.minute == 0 && startMinutes > 0) 24 * 60 else end.hour * 60 + end.minute
                                val durationMinutes = endMinutes - startMinutes

                                val topOffset = pixelsPerMinute * startMinutes
                                val itemHeight = (pixelsPerMinute * durationMinutes).coerceAtLeast(slotHeight)
                                val action = actions.find { it.id == block.actionId }
                                val folder = action?.let { a -> folders.find { it.id == a.folderId } }
                                val folderColor = folder?.color?.let { Color(it) }

                                TimeBlockItem(
                                    actionTitle = action?.title ?: "Unknown",
                                    folderColor = folderColor,
                                    itemHeight = itemHeight,
                                    modifier = Modifier
                                        .padding(1.dp)
                                        .fillMaxWidth()
                                        .offset(y = topOffset)
                                        .height(itemHeight),
                                    onClick = { onTimeBlockClick(block) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MonthViewContent(
    selectedDate: LocalDate,
    events: List<Event>,
    timeBlocks: List<TimeBlock>,
    actions: List<Action>,
    folders: List<Folder>,
    onDateClick: (LocalDate) -> Unit
) {
    val firstDayOfMonth = LocalDate(selectedDate.year, selectedDate.month, 1)
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.ordinal // 0 = Monday, 6 = Sunday.
    
    // Use DatePeriod addition to go to next month, then subtract one day to find length
    val lengthOfMonth = firstDayOfMonth.plus(DatePeriod(months = 1)).plus(DatePeriod(days = -1)).dayOfMonth

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
    ) {
        // Days Header
        Row(modifier = Modifier.fillMaxWidth()) {
            listOf("M", "T", "W", "T", "F", "S", "S").forEach { day ->
                Text(
                    text = day,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            var currentDay = 1
            var finished = false
            
            // 6 rows max usually covers a month
            repeat(6) { week ->
                if (finished) return@repeat
                
                Row(modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()) {
                    repeat(7) { dayOfWeek ->
                        if (week == 0 && dayOfWeek < firstDayOfWeek) {
                            Spacer(modifier = Modifier.weight(1f))
                        } else if (currentDay > lengthOfMonth) {
                            Spacer(modifier = Modifier.weight(1f))
                            finished = true
                        } else {
                            val date = LocalDate(selectedDate.year, selectedDate.month, currentDay)
                            val isToday = date == Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
                            val isSelected = date == selectedDate
                            
                            val dayEvents = events.filter { 
                                it.startDatetime.toLocalDateTime(TimeZone.currentSystemDefault()).date == date 
                            }
                            val dayBlocks = timeBlocks.filter { it.blockDate == date }

                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(1.dp)
                                    .clickable { onDateClick(date) },
                                colors = CardDefaults.cardColors(
                                    containerColor = if (isSelected) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surface
                                ),
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(2.dp)
                                ) {
                                    // Day number
                                    Text(
                                        text = currentDay.toString(),
                                        style = MaterialTheme.typography.labelSmall,
                                        fontSize = 10.sp,
                                        color = if (isToday) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                                        fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal
                                    )
                                    
                                    // Events and Actions list
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1f),
                                        verticalArrangement = Arrangement.spacedBy(1.dp)
                                    ) {
                                        // Data class to hold item info
                                        data class CalendarItem(val title: String, val isEvent: Boolean, val color: Color?)
                                        
                                        val maxItems = 3
                                        val allItems = mutableListOf<CalendarItem>()
                                        
                                        // Add events (purple/primaryContainer)
                                        dayEvents.forEach { event ->
                                            allItems.add(CalendarItem(event.title, true, null))
                                        }
                                        
                                        // Add time blocks with folder color
                                        dayBlocks.forEach { block ->
                                            val action = actions.find { it.id == block.actionId }
                                            val folder = action?.let { a -> folders.find { it.id == a.folderId } }
                                            val folderColor = folder?.color?.let { Color(it) }
                                            allItems.add(CalendarItem(action?.title ?: "Task", false, folderColor))
                                        }
                                        
                                        val itemsToShow = allItems.take(maxItems)
                                        val remaining = allItems.size - maxItems
                                        
                                        itemsToShow.forEach { item ->
                                            val bgColor = if (item.isEvent) {
                                                MaterialTheme.colorScheme.primaryContainer
                                            } else {
                                                item.color ?: Green500
                                            }
                                            val textColor = if (item.isEvent) {
                                                MaterialTheme.colorScheme.onPrimaryContainer
                                            } else {
                                                Color.White
                                            }
                                            
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .background(bgColor, RoundedCornerShape(2.dp))
                                                    .padding(horizontal = 2.dp, vertical = 1.dp)
                                            ) {
                                                Text(
                                                    text = item.title,
                                                    style = MaterialTheme.typography.labelSmall,
                                                    fontSize = 8.sp,
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis,
                                                    color = textColor
                                                )
                                            }
                                        }
                                        
                                        // Show "+N more" if there are more items
                                        if (remaining > 0) {
                                            Text(
                                                text = "+$remaining more",
                                                style = MaterialTheme.typography.labelSmall,
                                                fontSize = 7.sp,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }
                                }
                            }
                            currentDay++
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventItem(
    event: Event,
    modifier: Modifier = Modifier,
    itemHeight: Dp = 64.dp,
    onClick: () -> Unit
) {
    // Scale font size based on item height
    val fontSize = when {
        itemHeight < 24.dp -> 8.sp
        itemHeight < 32.dp -> 9.sp
        itemHeight < 48.dp -> 10.sp
        else -> 11.sp
    }
    val maxLines = when {
        itemHeight < 32.dp -> 1
        itemHeight < 48.dp -> 2
        else -> 3
    }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = event.title,
                fontSize = fontSize,
                fontWeight = FontWeight.Bold,
                maxLines = maxLines,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeBlockItem(
    actionTitle: String,
    modifier: Modifier = Modifier,
    folderColor: Color? = null,
    itemHeight: Dp = 64.dp,
    onClick: () -> Unit
) {
    // Scale font size based on item height
    val fontSize = when {
        itemHeight < 24.dp -> 8.sp
        itemHeight < 32.dp -> 9.sp
        itemHeight < 48.dp -> 10.sp
        else -> 11.sp
    }
    val maxLines = when {
        itemHeight < 32.dp -> 1
        itemHeight < 48.dp -> 2
        else -> 3
    }

    val containerColor = folderColor ?: Green500

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = actionTitle,
                fontSize = fontSize,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                maxLines = maxLines,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

