package com.example.lab3

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

object Routes {
    const val INPUT = "input"
    const val RESULT = "result"
    const val HISTORY = "history"
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel()
    val historyViewModel: HistoryViewModel = viewModel() // ← сюди винести

    NavHost(navController = navController, startDestination = Routes.INPUT) {
        composable(Routes.INPUT) {
            InputScreen(
                viewModel = viewModel,
                onNavigateToResult = { navController.navigate(Routes.RESULT) },
                onNavigateToHistory = { navController.navigate(Routes.HISTORY) }
            )
        }
        composable(Routes.RESULT) {
            ResultScreen(
                viewModel = viewModel,
                onCancel = {
                    viewModel.resetSelection()
                    navController.popBackStack()
                }
            )
        }
        composable(Routes.HISTORY) {
            HistoryScreen(
                historyViewModel = historyViewModel, // ← передаємо готовий
                onBack = { navController.popBackStack() }
            )
        }
    }
}