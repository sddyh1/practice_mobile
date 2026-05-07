package com.example.depositcalculator.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DetailScreen(navController: NavController, id: Long, viewModel: DetailViewModel = hiltViewModel()) {
    val calculation by viewModel.calculation.collectAsState()
    val dateFormat = remember { SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()) }
    LaunchedEffect(id) { viewModel.loadCalculation(id) }

    Column(Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Детали расчёта", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))
        if (calculation == null) CircularProgressIndicator()
        else {
            val c = calculation!!
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text("Дата: ${dateFormat.format(Date(c.calculationDate))}")
                    Text("Стартовый взнос: ${c.initialAmount} ₽")
                    Text("Срок: ${c.periodMonths} мес.")
                    Text("Ставка: ${c.interestRate}%")
                    if (c.monthlyTopUp != null && c.monthlyTopUp > 0) Text("Пополнение: ${c.monthlyTopUp} ₽")
                    Divider()
                    Text("Итог: ${String.format("%.2f", c.finalAmount)} ₽")
                    Text("Проценты: ${String.format("%.2f", c.interestEarned)} ₽")
                }
            }
        }
    }
}