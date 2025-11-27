package com.lifeos.app.ui.screens.notes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.lifeos.app.ui.theme.Green500
import com.lifeos.domain.model.Notebook
import kotlinx.datetime.Clock
import java.util.UUID

@Composable
fun NotebookDialog(
    notebook: Notebook? = null,
    onDismiss: () -> Unit,
    onSave: (Notebook) -> Unit,
    onDelete: (() -> Unit)? = null
) {
    var name by remember { mutableStateOf(notebook?.name ?: "") }

    val isEditing = notebook != null

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
                // Header with icon
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Book,
                        contentDescription = null,
                        tint = Green500,
                        modifier = Modifier.size(32.dp)
                    )
                    Text(
                        text = if (isEditing) "Edit Notebook" else "New Notebook",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Name Input
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Notebook Name") },
                    placeholder = { Text("Enter notebook name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Green500,
                        cursorColor = Green500,
                        focusedLabelColor = Green500
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Delete button (only for editing)
                    if (isEditing && onDelete != null && notebook?.isDefault != true) {
                        TextButton(
                            onClick = onDelete,
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = MaterialTheme.colorScheme.error
                            )
                        ) {
                            Text("Delete")
                        }
                    } else {
                        Spacer(modifier = Modifier.width(1.dp))
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        TextButton(onClick = onDismiss) {
                            Text("Cancel")
                        }
                        Button(
                            onClick = {
                                val now = Clock.System.now()
                                val notebookToSave = Notebook(
                                    id = notebook?.id ?: UUID.randomUUID().toString(),
                                    userId = notebook?.userId ?: "",
                                    name = name,
                                    isDefault = notebook?.isDefault ?: false,
                                    goalId = notebook?.goalId,
                                    createdAt = notebook?.createdAt ?: now,
                                    updatedAt = now
                                )
                                onSave(notebookToSave)
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
}

