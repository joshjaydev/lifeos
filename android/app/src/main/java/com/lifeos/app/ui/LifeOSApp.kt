package com.lifeos.app.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lifeos.app.R
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lifeos.app.ui.screens.actions.ActionsScreen
import com.lifeos.app.ui.screens.atman.AtmanScreen
import com.lifeos.app.ui.screens.notes.NotesScreen
import com.lifeos.app.ui.screens.time.TimeScreen
import com.lifeos.app.ui.screens.values.ValuesScreen

sealed class Screen(val route: String, val title: String, val icon: Any) {
    data object Notes : Screen("notes", "Notes", Icons.Default.Notes)
    data object Actions : Screen("actions", "Actions", Icons.Default.CheckCircle)
    data object Time : Screen("time", "Time", Icons.Default.CalendarMonth)
    data object Atman : Screen("atman", "Atman", R.drawable.neurology_24px)
    data object Values : Screen("values", "Values", Icons.Default.Favorite)
}

val bottomNavItems = listOf(
    Screen.Notes,
    Screen.Actions,
    Screen.Time,
    Screen.Atman,
    Screen.Values
)

@Composable
fun LifeOSApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar (
                containerColor = Color.Transparent
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                bottomNavItems.forEach { screen ->
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = MaterialTheme.colorScheme.background,
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = Color(0xFFB0B0B0), // Lighter gray
                            selectedTextColor = Color.White,
                            unselectedTextColor = Color(0xFFB0B0B0) // Lighter gray
                        ),

                        icon = {
                            val iconModifier = Modifier.size(28.dp) // Adjusted icon size
                            when (val icon = screen.icon) {
                                is ImageVector -> Icon(imageVector = icon, contentDescription = screen.title, modifier = iconModifier)
                                is Int -> Icon(painter = painterResource(icon), contentDescription = screen.title, modifier = iconModifier)
                            }
                        },
                        label = { Text(text = screen.title) }, // Adjusted padding
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Notes.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Notes.route) { NotesScreen() }
            composable(Screen.Actions.route) { ActionsScreen() }
            composable(Screen.Time.route) { TimeScreen() }
            composable(Screen.Atman.route) { AtmanScreen() }
            composable(Screen.Values.route) { ValuesScreen() }
        }
    }
}
