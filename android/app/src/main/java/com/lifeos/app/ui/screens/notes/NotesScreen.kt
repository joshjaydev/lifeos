package com.lifeos.app.ui.screens.notes

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
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifeos.app.ui.components.CustomFilledIconButton
import com.lifeos.app.ui.theme.Green500
import com.lifeos.domain.model.Note
import com.lifeos.domain.model.Notebook
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesScreen(
    viewModel: NotesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    var showNotebookDialog by remember { mutableStateOf(false) }
    var editingNotebook by remember { mutableStateOf<Notebook?>(null) }
    var showNotebookContextMenu by remember { mutableStateOf(false) }
    var contextMenuNotebook by remember { mutableStateOf<Notebook?>(null) }
    
    var showNoteEditor by remember { mutableStateOf(false) }
    var editingNote by remember { mutableStateOf<Note?>(null) }

    // Get the selected notebook for creating notes
    val selectedNotebook = uiState.notebooks.find { it.id == uiState.selectedNotebookId }
        ?: uiState.notebooks.find { it.isDefault }
        ?: uiState.notebooks.firstOrNull()

    // Note editor screen
    if (showNoteEditor) {
        NoteEditorScreen(
            note = editingNote,
            notebook = if (editingNote != null) {
                uiState.notebooks.find { it.id == editingNote?.notebookId }
            } else {
                selectedNotebook
            },
            onBack = {
                showNoteEditor = false
                editingNote = null
            },
            onSave = { note ->
                if (editingNote != null) {
                    viewModel.updateNote(note)
                } else {
                    viewModel.createNote(note)
                }
            },
            onDelete = { noteId ->
                viewModel.deleteNote(noteId)
            }
        )
        return
    }

    Scaffold { innerPadding ->
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

            // Notebooks section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Notebooks",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                CustomFilledIconButton(
                    onClick = {
                        editingNotebook = null
                        showNotebookDialog = true
                    },
                    icon = Icons.Default.Add,
                    contentDescription = "Add notebook"
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Notebook cards
            if (uiState.notebooks.isEmpty()) {
                Text(
                    text = "No notebooks yet. Create your first notebook!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // "All" notebook option
                    item {
                        NotebookCard(
                            name = "All",
                            isSelected = uiState.selectedNotebookId == null,
                            onClick = { viewModel.selectNotebook(null) },
                            onLongPress = { /* No long press for "All" */ }
                        )
                    }

                    items(uiState.notebooks) { notebook ->
                        NotebookCard(
                            name = notebook.name,
                            isSelected = uiState.selectedNotebookId == notebook.id,
                            onClick = { viewModel.selectNotebook(notebook.id) },
                            onLongPress = {
                                contextMenuNotebook = notebook
                                showNotebookContextMenu = true
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Notes section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Notes",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                CustomFilledIconButton(
                    onClick = {
                        editingNote = null
                        showNoteEditor = true
                    },
                    icon = Icons.Default.Add,
                    contentDescription = "Add note"
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Notes list
            if (uiState.notes.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Description,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                        )
                        Text(
                            text = "No notes yet",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "Tap + to create your first note",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                        )
                    }
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(uiState.notes) { note ->
                        val noteNotebook = uiState.notebooks.find { it.id == note.notebookId }
                        NoteCard(
                            note = note,
                            notebookName = noteNotebook?.name ?: "Unknown",
                            onClick = {
                                editingNote = note
                                showNoteEditor = true
                            }
                        )
                    }
                }
            }
        }
    }

    // Notebook Dialog
    if (showNotebookDialog) {
        NotebookDialog(
            notebook = editingNotebook,
            onDismiss = {
                showNotebookDialog = false
                editingNotebook = null
            },
            onSave = { notebook ->
                if (editingNotebook != null) {
                    viewModel.updateNotebook(notebook)
                } else {
                    viewModel.createNotebook(notebook)
                }
            },
            onDelete = editingNotebook?.let { nb ->
                if (!nb.isDefault) {
                    { viewModel.deleteNotebook(nb.id) }
                } else null
            }
        )
    }

    // Notebook Context Menu
    if (showNotebookContextMenu && contextMenuNotebook != null) {
        AlertDialog(
            onDismissRequest = {
                showNotebookContextMenu = false
                contextMenuNotebook = null
            },
            title = { Text(contextMenuNotebook!!.name) },
            text = { Text("What would you like to do?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        editingNotebook = contextMenuNotebook
                        showNotebookContextMenu = false
                        contextMenuNotebook = null
                        showNotebookDialog = true
                    }
                ) {
                    Text("Edit")
                }
            },
            dismissButton = {
                if (contextMenuNotebook?.isDefault != true) {
                    TextButton(
                        onClick = {
                            contextMenuNotebook?.let { viewModel.deleteNotebook(it.id) }
                            showNotebookContextMenu = false
                            contextMenuNotebook = null
                        },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Text("Delete")
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
private fun NotebookCard(
    name: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    onLongPress: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(100.dp)
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
            horizontalAlignment = Alignment.Start
        ) {
            Icon(
                Icons.Outlined.Book,
                contentDescription = null,
                tint = if (isSelected) Green500 else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NoteCard(
    note: Note,
    notebookName: String,
    onClick: () -> Unit
) {
    val dateFormatter = remember { DateTimeFormatter.ofPattern("MMM d, yyyy") }
    val createdDate = note.createdAt.toLocalDateTime(TimeZone.currentSystemDefault())
    val formattedDate = try {
        createdDate.date.toJavaLocalDate().format(dateFormatter)
    } catch (e: Exception) {
        "Unknown date"
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Note info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(6.dp))

                // Metadata row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Notebook badge
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Text(
                            text = notebookName,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    
                    Text(
                        text = "•",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                    )
                    
                    Text(
                        text = formattedDate,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            // Note icon
            Icon(
                imageVector = Icons.Outlined.Description,
                contentDescription = null,
                tint = Green500,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

// Extension to convert kotlinx LocalDate to java LocalDate
private fun kotlinx.datetime.LocalDate.toJavaLocalDate(): java.time.LocalDate {
    return java.time.LocalDate.of(this.year, this.monthNumber, this.dayOfMonth)
}
