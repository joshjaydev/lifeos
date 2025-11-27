package com.lifeos.app.ui.screens.actions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.lifeos.app.ui.theme.Green500
import com.lifeos.domain.model.Folder
import kotlinx.datetime.Clock

// Predefined folder colors
val folderColors = listOf(
    Color(0xFFEF5350), // Red
    Color(0xFFEC407A), // Pink
    Color(0xFFAB47BC), // Purple
    Color(0xFF7E57C2), // Deep Purple
    Color(0xFF5C6BC0), // Indigo
    Color(0xFF42A5F5), // Blue
    Color(0xFF29B6F6), // Light Blue
    Color(0xFF26C6DA), // Cyan
    Color(0xFF26A69A), // Teal
    Color(0xFF32CD33), // LifeOS Green (Green500)
    Color(0xFF9CCC65), // Light Green
    Color(0xFFD4E157), // Lime
    Color(0xFFFFEE58), // Yellow
    Color(0xFFFFCA28), // Amber
    Color(0xFFFF7043), // Deep Orange
    Color(0xFF8D6E63), // Brown
    Color(0xFF78909C), // Blue Grey
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FolderDialog(
    folder: Folder? = null, // null for create, non-null for edit
    onDismiss: () -> Unit,
    onSave: (Folder) -> Unit,
    onDelete: (() -> Unit)? = null
) {
    var name by remember { mutableStateOf(folder?.name ?: "") }
    var selectedColor by remember { mutableStateOf(folder?.color ?: folderColors[9].toArgb().toLong()) }
    var showDeleteConfirmation by remember { mutableStateOf(false) }

    val isEditing = folder != null

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
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text("Folder Name", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)) },
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
                
                // Divider
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                Spacer(modifier = Modifier.height(16.dp))

                // Color Picker Section
                Text(
                    text = "Folder Color",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    folderColors.forEach { color ->
                        val colorLong = color.toArgb().toLong()
                        val isSelected = colorLong == selectedColor
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(color)
                                .border(
                                    width = if (isSelected) 3.dp else 1.dp,
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent, // Only border if selected or subtle? Changed to transparent if not selected for cleaner look
                                    shape = CircleShape
                                )
                                .clickable { selectedColor = colorLong },
                            contentAlignment = Alignment.Center
                        ) {
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Selected",
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isEditing && onDelete != null && folder?.isDefault == false) {
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
                                val folderToSave = Folder(
                                    id = folder?.id ?: "",
                                    userId = folder?.userId ?: "",
                                    name = name,
                                    color = selectedColor,
                                    isDefault = folder?.isDefault ?: false,
                                    goalId = folder?.goalId,
                                    createdAt = folder?.createdAt ?: now,
                                    updatedAt = now
                                )
                                onSave(folderToSave)
                                onDismiss()
                            },
                            enabled = name.isNotBlank(),
                            colors = ButtonDefaults.buttonColors(containerColor = Green500)
                        ) {
                            Text(if (isEditing) "Save" else "Create")
                        }
                    }
                }
            }
        }
    }

    // Delete confirmation dialog
    if (showDeleteConfirmation && onDelete != null) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            title = { Text("Delete Folder") },
            text = { Text("Are you sure you want to delete this folder? All actions in this folder will also be deleted. This action cannot be undone.") },
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