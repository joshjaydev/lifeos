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
import com.lifeos.domain.model.Principle
import kotlinx.datetime.Clock

@Composable
fun PrincipleDialog(
    principle: Principle? = null,
    onDismiss: () -> Unit,
    onSave: (Principle) -> Unit,
    onDelete: (() -> Unit)? = null
) {
    var fundamentalTruth by remember { mutableStateOf(principle?.fundamentalTruth ?: "") }
    var experience by remember { mutableStateOf(principle?.experience ?: "") }
    var showDeleteConfirmation by remember { mutableStateOf(false) }
    val isEditing = principle != null

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
                // Header
                Text(
                    text = if (isEditing) "Edit Principle" else "New Principle",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                TextField(
                    value = fundamentalTruth,
                    onValueChange = { fundamentalTruth = it },
                    placeholder = { Text("Fundamental Truth", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)) },
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

                TextField(
                    value = experience,
                    onValueChange = { experience = it },
                    placeholder = { Text("Failures/Experiences that led to this principle...", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)) },
                    textStyle = MaterialTheme.typography.bodyLarge,
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
                                val newPrinciple = Principle(
                                    id = principle?.id ?: java.util.UUID.randomUUID().toString(),
                                    userId = principle?.userId ?: "",
                                    parentId = principle?.parentId,
                                    fundamentalTruth = fundamentalTruth,
                                    experience = experience.takeIf { it.isNotBlank() },
                                    createdAt = principle?.createdAt ?: now,
                                    updatedAt = now
                                )
                                onSave(newPrinciple)
                                onDismiss()
                            },
                            enabled = fundamentalTruth.isNotBlank(),
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
            title = { Text("Delete Principle") },
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
