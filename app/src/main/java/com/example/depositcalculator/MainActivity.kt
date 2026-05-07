package com.example.depositcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.depositcalculator.presentation.SharedDepositViewModel
import com.example.depositcalculator.presentation.detail.DetailScreen
import com.example.depositcalculator.presentation.first_step.FirstStepScreen
import com.example.depositcalculator.presentation.history.HistoryScreen
import com.example.depositcalculator.presentation.main.MainScreen
import com.example.depositcalculator.presentation.result.ResultScreen
import com.example.depositcalculator.presentation.second_step.SecondStepScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    val sharedViewModel: SharedDepositViewModel = viewModel()

                    NavHost(navController, startDestination = "main") {
                        composable("main") { MainScreen(navController) }
                        composable("first_step") { FirstStepScreen(navController, sharedViewModel) }
                        composable("second_step") { SecondStepScreen(navController, sharedViewModel) }
                        composable("result") { ResultScreen(navController, sharedViewModel) }
                        composable("history") { HistoryScreen(navController) }
                        composable("detail/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toLongOrNull() ?: 0L
                            DetailScreen(navController, id)
                        }
                    }
                }
            }
        }
    }
}