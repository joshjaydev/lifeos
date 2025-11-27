package com.lifeos.app.ui.screens.values

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.lifeos.app.ui.theme.Green500
import com.lifeos.domain.model.Goal
import kotlinx.datetime.Clock

@Composable
fun GoalDialog(
    goal: Goal? = null,
    onDismiss: () -> Unit,
    onSave: (Goal) -> Unit,
    onDelete: (() -> Unit)? = null
) {
    var whatDoYouWant by remember { mutableStateOf(goal?.whatDoYouWant ?: "") }
    var showDeleteConfirmation by remember { mutableStateOf(false) }
    val isEditing = goal != null

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
                Text(
                    text = "What do you want?",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                TextField(
                    value = whatDoYouWant,
                    onValueChange = { whatDoYouWant = it },
                    placeholder = { Text("I want to...", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)) },
                    textStyle = MaterialTheme.typography.headlineSmall,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

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
                                val newGoal = Goal(
                                    id = goal?.id ?: java.util.UUID.randomUUID().toString(),
                                    userId = goal?.userId ?: "",
                                    whatDoYouWant = whatDoYouWant,
                                    folderId = goal?.folderId,
                                    notebookId = goal?.notebookId,
                                    createdAt = goal?.createdAt ?: now,
                                    updatedAt = now
                                )
                                onSave(newGoal)
                                onDismiss()
                            },
                            enabled = whatDoYouWant.isNotBlank(),
                            colors = ButtonDefaults.buttonColors(containerColor = Green500)
                        ) {
                            Text("Save")
                        }
                    }
                }
            }
        }
    }

    if (showDeleteConfirmation && onDelete != null) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            title = { Text("Delete Goal") },
            text = { Text("Are you sure? This cannot be undone.") },
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
                TextButton(onClick = { showDeleteConfirmation = false }) { Text("Cancel") }
            }
        )
    }
}
