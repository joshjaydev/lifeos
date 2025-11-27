package com.lifeos.app.ui.screens.actions

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifeos.app.ui.theme.Green500
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter
import com.lifeos.util.toAmPmString // Added import

import com.lifeos.app.ui.components.CustomFilledIconButton // Added import

@Composable
fun ActionsScreen(
    viewModel: ActionsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showActionDialog by remember { mutableStateOf(false) }
    var showFolderDialog by remember { mutableStateOf(false) }
    var editingAction by remember { mutableStateOf<com.lifeos.domain.model.Action?>(null) }
    var editingFolder by remember { mutableStateOf<com.lifeos.domain.model.Folder?>(null) }
    
    // State for showing context menu on folder
    var showFolderContextMenu by remember { mutableStateOf(false) }
    var contextMenuFolder by remember { mutableStateOf<com.lifeos.domain.model.Folder?>(null) }

Scaffold(
) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Show error if any
            uiState.error?.let { error ->
                Snackbar(
                    modifier = Modifier.padding(8.dp),
                    action = {
                        TextButton(onClick = { viewModel.clearError() }) {
                            Text("Dismiss")
                        }
                    }
                ) {
                    Text(error)
                }
            }

            // Folders section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Folders",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                CustomFilledIconButton(
                    onClick = {
                        editingFolder = null
                        showFolderDialog = true
                    },
                    icon = Icons.Default.Add,
                    contentDescription = "Add folder"
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Folder cards
            if (uiState.folders.isEmpty()) {
                Text(
                    text = "No folders yet. Create your first folder!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // "All" folder option
                    item {
                        FolderCard(
                            name = "All",
                            isSelected = uiState.selectedFolderId == null,
                            onClick = { viewModel.selectFolder(null) },
                            onLongPress = { /* No long press for "All" folder */ }
                        )
                    }

                    items(uiState.folders) { folder ->
                        FolderCard(
                            name = folder.name,
                            color = folder.color,
                            isSelected = uiState.selectedFolderId == folder.id,
                            onClick = { viewModel.selectFolder(folder.id) },
                            onLongPress = {
                                contextMenuFolder = folder
                                showFolderContextMenu = true
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Actions section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Actions",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CustomFilledIconButton(
                        onClick = {
                            editingAction = null
                            showActionDialog = true
                        },
                        icon = Icons.Default.Add,
                        contentDescription = "Add action"
                    )
                    if (uiState.isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            // Actions list
            if (uiState.actions.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No actions yet. Create your first action!",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.actions) { action ->
                        val folderColor = uiState.folders.find { it.id == action.folderId }?.color
                        ActionCard(
                            action = action,
                            folderColor = folderColor,
                            onComplete = { viewModel.completeAction(action.id) },
                            onEdit = {
                                editingAction = action
                                showActionDialog = true
                            }
                        )
                    }
                }
            }
        }
    }

    // Action Dialog
    if (showActionDialog) {
        ActionDialog(
            action = editingAction,
            folders = uiState.folders,
            onDismiss = {
                showActionDialog = false
                editingAction = null
            },
            onSave = { action ->
                if (editingAction != null) {
                    viewModel.updateAction(action)
                } else {
                    viewModel.createAction(action)
                }
            },
            onDelete = editingAction?.let {
                { viewModel.deleteAction(it.id) }
            }
        )
    }

    // Folder Dialog
    if (showFolderDialog) {
        FolderDialog(
            folder = editingFolder,
            onDismiss = {
                showFolderDialog = false
                editingFolder = null
            },
            onSave = { folder ->
                if (editingFolder != null) {
                    viewModel.updateFolder(folder)
                } else {
                    viewModel.createFolder(folder)
                }
            },
            onDelete = editingFolder?.let {
                { viewModel.deleteFolder(it.id) }
            }
        )
    }

    // Folder Context Menu
    if (showFolderContextMenu && contextMenuFolder != null) {
        AlertDialog(
            onDismissRequest = { 
                showFolderContextMenu = false
                contextMenuFolder = null
            },
            title = { Text(contextMenuFolder!!.name) },
            text = { Text("What would you like to do?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        editingFolder = contextMenuFolder
                        showFolderContextMenu = false
                        contextMenuFolder = null
                        showFolderDialog = true
                    }
                ) {
                    Text("Edit")
                }
            },
            dismissButton = {
                if (contextMenuFolder?.isDefault != true) {
                    TextButton(
                        onClick = {
                            contextMenuFolder?.let { viewModel.deleteFolder(it.id) }
                            showFolderContextMenu = false
                            contextMenuFolder = null
                        },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Text("Delete")
                    }
                    TextButton(
                        onClick = { 
                            showFolderContextMenu = false
                            contextMenuFolder = null
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            },
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            shape = RoundedCornerShape(28.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FolderCard(
    name: String,
    color: Long? = null,
    isSelected: Boolean,
    onClick: () -> Unit,
    onLongPress: () -> Unit 
) {
    Card(
        modifier = Modifier
            .width(100.dp)
             // Removing fixed height to let it size naturally or restore previous look
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongPress 
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                MaterialTheme.colorScheme.surfaceVariant
            else
                MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.Start // Restore original alignment
        ) {
            Icon(
                Icons.Default.Folder,
                contentDescription = null,
                tint = color?.let { androidx.compose.ui.graphics.Color(it.toInt()) } ?: Green500,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun ActionCard(
    action: com.lifeos.domain.model.Action,
    folderColor: Long?, 
    onComplete: () -> Unit,
    onEdit: () -> Unit
) {
    var isChecked by remember { mutableStateOf(false) }

    val alpha by animateFloatAsState(
        targetValue = if (isChecked) 0f else 1f,
        animationSpec = tween(durationMillis = 600), // Longer animation
        label = "alpha"
    )
    val scale by animateFloatAsState(
        targetValue = if (isChecked) 0.8f else 1f,
        animationSpec = tween(durationMillis = 600), // Longer animation
        label = "scale"
    )

    LaunchedEffect(isChecked) {
        if (isChecked) {
            kotlinx.coroutines.delay(600) // Match animation duration
            onComplete()
        }
    }

    // The item stays in the composition tree until onComplete triggers removal from the list data.
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                this.alpha = alpha
                this.scaleX = scale
                this.scaleY = scale
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = folderColor?.let { Color(it.toInt()) } ?: Green500,
                        uncheckedColor = folderColor?.let { Color(it.toInt()) } ?: Green500
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = action.title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White // Title white
                    )

                    val dateText = action.dueDate?.let { formatDate(it) }
                    val timeText = action.dueTime?.toAmPmString() // Use AM/PM format

                    val dueString = listOfNotNull(dateText, timeText).joinToString(" at ")
                    
                    if (dueString.isNotBlank()) {
                        Text(
                            text = "Due: $dueString",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            IconButton(onClick = onEdit) {
                Icon(
                    Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Green500
                )
            }
        }
    }
}
private fun formatDate(instant: Instant): String {
    val localDate = instant.toLocalDateTime(TimeZone.currentSystemDefault()).date
    val javaDate = localDate.toJavaLocalDate()
    val formatter = DateTimeFormatter.ofPattern("MMM d")
    return javaDate.format(formatter)
}