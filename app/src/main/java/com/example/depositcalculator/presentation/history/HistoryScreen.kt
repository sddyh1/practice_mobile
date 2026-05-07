package com.example.depositcalculator.presentation.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HistoryScreen(navController: NavController, viewModel: HistoryViewModel = hiltViewModel()) {
    val calculations by viewModel.calculations.collectAsState()
    val dateFormat = remember { SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()) }
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("История расчётов", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(calculations) { calc ->
                Card(modifier = Modifier.fillMaxWidth(),
                    onClick = { navController.navigate("detail/${calc.id}") }) {
                    Column(Modifier.padding(12.dp)) {
                        Text(dateFormat.format(Date(calc.calculationDate)), style = MaterialTheme.typography.labelSmall)
                        Text("Стартовый взнос: ${calc.initialAmount} ₽")
                        Text("Итог: ${String.format("%.2f", calc.finalAmount)} ₽")
                    }
                }
            }
        }
    }
}