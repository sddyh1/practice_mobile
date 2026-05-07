package com.example.depositcalculator.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text("Расчёт вкладов", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = { navController.navigate("first_step") }) { Text("Рассчитать") }
        Button(onClick = { navController.navigate("history") }) { Text("История расчётов") }
        Button(onClick = { (context as? android.app.Activity)?.finishAffinity() }) { Text("Закрыть приложение") }
    }
}