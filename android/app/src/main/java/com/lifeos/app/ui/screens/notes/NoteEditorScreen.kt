package com.lifeos.app.ui.screens.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Undo
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lifeos.app.ui.theme.Green500
import com.lifeos.domain.model.Note
import com.lifeos.domain.model.Notebook
import kotlinx.datetime.Clock
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditorScreen(
    note: Note? = null,
    notebook: Notebook?,
    onBack: () -> Unit,
    onSave: (Note) -> Unit,
    onDelete: ((String) -> Unit)? = null
) {
    var title by remember { mutableStateOf(note?.title ?: "") }
    var contentValue by remember { 
        mutableStateOf(TextFieldValue(note?.content ?: "")) 
    }
    var showDeleteConfirmation by remember { mutableStateOf(false) }
    var showFontSizeMenu by remember { mutableStateOf(false) }
    
    // Undo history
    val undoHistory = remember { mutableStateListOf<TextFieldValue>() }
    val maxUndoHistory = 50
    
    // Track content changes for undo
    fun updateContentWithHistory(newValue: TextFieldValue) {
        if (newValue.text != contentValue.text) {
            // Only save to history if text actually changed
            undoHistory.add(contentValue)
            if (undoHistory.size > maxUndoHistory) {
                undoHistory.removeAt(0)
            }
        }
        contentValue = newValue
    }
    
    fun undo() {
        if (undoHistory.isNotEmpty()) {
            contentValue = undoHistory.removeLast()
        }
    }
    
    // Formatting state
    var fontSize by remember { mutableStateOf(16) } // Default font size

    val isEditing = note != null
    val titleFocusRequester = remember { FocusRequester() }
    val contentFocusRequester = remember { FocusRequester() }
    val scrollState = rememberScrollState()

    // Auto-focus title for new notes
    LaunchedEffect(Unit) {
        if (!isEditing) {
            titleFocusRequester.requestFocus()
        }
    }

    // Auto-save function
    fun saveNote() {
        val now = Clock.System.now()
        val noteToSave = Note(
            id = note?.id ?: UUID.randomUUID().toString(),
            userId = note?.userId ?: "",
            notebookId = notebook?.id ?: note?.notebookId ?: "",
            title = title.ifBlank { "Untitled" },
            content = contentValue.text,
            isJournal = note?.isJournal ?: false,
            journalDate = note?.journalDate,
            createdAt = note?.createdAt ?: now,
            updatedAt = now
        )
        onSave(noteToSave)
    }

    // Helper function to add bullet point
    fun addBullet() {
        val selection = contentValue.selection
        val text = contentValue.text
        
        // Find the start of the current line
        val lineStart = text.lastIndexOf('\n', (selection.start - 1).coerceAtLeast(0)) + 1
        
        // Check if line already has bullet
        val lineContent = text.substring(lineStart)
        val currentLineHasBullet = lineContent.startsWith("• ")
        
        if (currentLineHasBullet) {
            // Remove bullet
            val newText = text.substring(0, lineStart) + text.substring(lineStart + 2)
            contentValue = TextFieldValue(
                text = newText,
                selection = TextRange((selection.start - 2).coerceAtLeast(lineStart))
            )
        } else {
            // Add bullet
            val newText = text.substring(0, lineStart) + "• " + text.substring(lineStart)
            contentValue = TextFieldValue(
                text = newText,
                selection = TextRange(selection.start + 2)
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Book,
                            contentDescription = null,
                            tint = Green500,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = notebook?.name ?: "Note",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        // Auto-save on exit
                        if (title.isNotBlank() || contentValue.text.isNotBlank()) {
                            saveNote()
                        }
                        onBack()
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    // Delete button (only for existing notes)
                    if (isEditing && onDelete != null) {
                        IconButton(
                            onClick = { showDeleteConfirmation = true }
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .imePadding()
        ) {
            // Scrollable content - takes all remaining space
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState)
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

            // Title TextField
            BasicTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(titleFocusRequester),
                textStyle = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                cursorBrush = SolidColor(Green500),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                ),
                decorationBox = { innerTextField ->
                    Box {
                        if (title.isEmpty()) {
                            Text(
                                text = "Title",
                                style = TextStyle(
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                                )
                            )
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Divider
            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant,
                thickness = 1.dp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Content TextField with dynamic font size
            BasicTextField(
                value = contentValue,
                onValueChange = { updateContentWithHistory(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 400.dp)
                    .focusRequester(contentFocusRequester),
                textStyle = TextStyle(
                    fontSize = fontSize.sp,
                    lineHeight = (fontSize * 1.5).sp,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                cursorBrush = SolidColor(Green500),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                ),
                decorationBox = { innerTextField ->
                    Box {
                        if (contentValue.text.isEmpty()) {
                            Text(
                                text = "Start writing...",
                                style = TextStyle(
                                    fontSize = fontSize.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                                )
                            )
                        }
                        innerTextField()
                    }
                }
            )
            }
            
            // Formatting toolbar - stays at bottom, moves up with keyboard
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Bullet list button
                IconButton(
                    onClick = { addBullet() },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        Icons.Default.FormatListBulleted,
                        contentDescription = "Bullet List",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(22.dp)
                    )
                }
                
                // Undo button
                IconButton(
                    onClick = { undo() },
                    modifier = Modifier.size(40.dp),
                    enabled = undoHistory.isNotEmpty()
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.Undo,
                        contentDescription = "Undo",
                        tint = if (undoHistory.isNotEmpty()) 
                            MaterialTheme.colorScheme.onSurface 
                        else 
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                        modifier = Modifier.size(22.dp)
                    )
                }
                
                // Divider
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .height(24.dp)
                        .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                )
                
                // Font size selector
                Box {
                    Row(
                        modifier = Modifier
                            .clickable { showFontSizeMenu = true }
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            Icons.Default.FormatSize,
                            contentDescription = "Font Size",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "${fontSize}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    
                    DropdownMenu(
                        expanded = showFontSizeMenu,
                        onDismissRequest = { showFontSizeMenu = false }
                    ) {
                        listOf(12, 14, 16, 18, 20, 24, 28, 32).forEach { size ->
                            DropdownMenuItem(
                                text = { 
                                    Text(
                                        "$size",
                                        fontWeight = if (size == fontSize) FontWeight.Bold else FontWeight.Normal,
                                        color = if (size == fontSize) Green500 else MaterialTheme.colorScheme.onSurface
                                    ) 
                                },
                                onClick = {
                                    fontSize = size
                                    showFontSizeMenu = false
                                },
                                leadingIcon = if (size == fontSize) {
                                    {
                                        Box(
                                            modifier = Modifier
                                                .size(8.dp)
                                                .background(Green500, RoundedCornerShape(4.dp))
                                        )
                                    }
                                } else null
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                // Character count
                Text(
                    text = "${contentValue.text.length}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }

    // Delete Confirmation Dialog
    if (showDeleteConfirmation) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            title = { Text("Delete Note") },
            text = { Text("Are you sure you want to delete this note? This action cannot be undone.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        note?.let { onDelete?.invoke(it.id) }
                        showDeleteConfirmation = false
                        onBack()
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirmation = false }) {
                    Text("Cancel")
                }
            },
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            shape = RoundedCornerShape(28.dp)
        )
    }
}
