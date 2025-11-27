package com.lifeos.app.ui.screens.atman

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifeos.app.ui.theme.Green500
import com.lifeos.domain.model.AiSuggestion
import com.lifeos.domain.model.ChatMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AtmanScreen(
    viewModel: AtmanViewModel = hiltViewModel()
) {
    val messages by viewModel.messages.collectAsState()
    val pendingSuggestions by viewModel.pendingSuggestions.collectAsState()
    var inputText by remember { mutableStateOf(TextFieldValue("")) }
    val listState = rememberLazyListState()

    // Scroll to bottom when new messages arrive
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Atman AI",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            IconButton(onClick = { viewModel.clearHistory() }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Clear History",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Chat List
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(messages) { message ->
                ChatMessageItem(message)
                
                // Check if there are suggestions linked to this message (simplification: just showing pending ones at bottom for now, 
                // but could filter by messageId if we had that link exposed clearly).
                // For this prototype, we'll display pending suggestions in a separate overlay or floating section, 
                // OR interleaved if we can match them. 
                // Let's just show pending suggestions that match the message ID if possible, or just show them.
            }
            
            // Show pending suggestions at the bottom of the chat
            if (pendingSuggestions.isNotEmpty()) {
                item {
                    Text(
                        text = "Suggestions:",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                items(pendingSuggestions) { suggestion ->
                    SuggestionCard(
                        suggestion = suggestion,
                        onAccept = { viewModel.acceptSuggestion(suggestion) },
                        onDecline = { viewModel.declineSuggestion(suggestion) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Input Area
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                placeholder = { Text("Ask Atman...") },
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = 56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Green500,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = {
                    if (inputText.text.isNotBlank()) {
                        viewModel.sendMessage(inputText.text)
                        inputText = TextFieldValue("")
                    }
                },
                modifier = Modifier
                    .size(56.dp)
                    .background(Green500, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Send",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun ChatMessageItem(message: ChatMessage) {
    val isUser = message.role == com.lifeos.domain.model.MessageRole.USER
    val alignment = if (isUser) Alignment.End else Alignment.Start
    val containerColor = if (isUser) Green500 else MaterialTheme.colorScheme.surfaceVariant
    val contentColor = if (isUser) Color.White else MaterialTheme.colorScheme.onSurface

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = alignment
    ) {
        Card(
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp,
                bottomStart = if (isUser) 20.dp else 4.dp,
                bottomEnd = if (isUser) 4.dp else 20.dp
            ),
            colors = CardDefaults.cardColors(containerColor = containerColor)
        ) {
            Text(
                text = message.content,
                modifier = Modifier.padding(16.dp),
                color = contentColor,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun SuggestionCard(
    suggestion: AiSuggestion,
    onAccept: () -> Unit,
    onDecline: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "AI Suggestion: ${suggestion.suggestionType.name}",
                style = MaterialTheme.typography.labelMedium,
                color = Green500
            )
            Spacer(modifier = Modifier.height(4.dp))
            // We are displaying raw data for now, ideally this would be a summary
            Text(
                text = "Data: ${suggestion.suggestionData}", 
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = onDecline) {
                    Text("Decline", color = MaterialTheme.colorScheme.error)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = onAccept,
                    colors = ButtonDefaults.buttonColors(containerColor = Green500)
                ) {
                    Text("Accept")
                }
            }
        }
    }
}