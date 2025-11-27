package com.lifeos.app.ui.screens.values

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lifeos.app.ui.theme.Green500
import com.lifeos.domain.model.Goal
import com.lifeos.domain.model.Principle

@Composable
fun ValuesScreen(
    viewModel: ValuesViewModel = hiltViewModel()
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Principles", "Goals")
    
    val principles by viewModel.principles.collectAsState()
    val goals by viewModel.goals.collectAsState()

    var showPrincipleDialog by remember { mutableStateOf(false) }
    var showGoalDialog by remember { mutableStateOf(false) }
    var editingPrinciple by remember { mutableStateOf<Principle?>(null) }
    var editingGoal by remember { mutableStateOf<Goal?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Tab row
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onSurface
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.weight(1f)) {
            when (selectedTab) {
                0 -> PrinciplesContent(
                    principles = principles,
                    onEdit = {
                        editingPrinciple = it
                        showPrincipleDialog = true
                    }
                )
                1 -> GoalsContent(
                    goals = goals,
                    onEdit = {
                        editingGoal = it
                        showGoalDialog = true
                    }
                )
            }

            FloatingActionButton(
                onClick = {
                    if (selectedTab == 0) {
                        editingPrinciple = null
                        showPrincipleDialog = true
                    } else {
                        editingGoal = null
                        showGoalDialog = true
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = Green500
            ) {
                Icon(Icons.Default.Add, contentDescription = if (selectedTab == 0) "Add principle" else "Add goal")
            }
        }
    }

    if (showPrincipleDialog) {
        PrincipleDialog(
            principle = editingPrinciple,
            onDismiss = {
                showPrincipleDialog = false
                editingPrinciple = null
            },
            onSave = { principle ->
                if (editingPrinciple != null) {
                    viewModel.updatePrinciple(principle)
                } else {
                    viewModel.createPrinciple(principle)
                }
            },
            onDelete = editingPrinciple?.let {
                { viewModel.deletePrinciple(it.id) }
            }
        )
    }

    if (showGoalDialog) {
        GoalDialog(
            goal = editingGoal,
            onDismiss = {
                showGoalDialog = false
                editingGoal = null
            },
            onSave = { goal ->
                if (editingGoal != null) {
                    viewModel.updateGoal(goal)
                } else {
                    viewModel.createGoal(goal)
                }
            },
            onDelete = editingGoal?.let {
                { viewModel.deleteGoal(it.id) }
            }
        )
    }
}

@Composable
private fun PrinciplesContent(
    principles: List<Principle>,
    onEdit: (Principle) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(principles) { principle ->
            PrincipleCard(principle = principle, onClick = { onEdit(principle) })
        }
    }
}

@Composable
private fun PrincipleCard(
    principle: Principle,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = principle.fundamentalTruth,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            if (!principle.experience.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = principle.experience!!,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun GoalsContent(
    goals: List<Goal>,
    onEdit: (Goal) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(goals) { goal ->
            GoalCard(goal = goal, onClick = { onEdit(goal) })
        }
    }
}

@Composable
private fun GoalCard(
    goal: Goal,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = goal.whatDoYouWant,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
