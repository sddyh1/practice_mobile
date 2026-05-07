package com.example.depositcalculator.presentation.second_step

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.depositcalculator.domain.usecase.CalculateDepositUseCase
import com.example.depositcalculator.presentation.SharedDepositViewModel

@Composable
fun SecondStepScreen(navController: NavController, vm: SharedDepositViewModel) {
    val months = vm.months.value
    val availableRates = CalculateDepositUseCase().getAvailableRate(months)
    var selectedRate by remember { mutableStateOf(availableRates) }
    var topUpStr by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Дополнительные параметры", style = MaterialTheme.typography.headlineSmall)
        Text("Срок: $months мес.")
        // Выпадающий список (хотя он один, но для выполнения условия)
        Box {
            Button(onClick = { expanded = true }) { Text("Ставка: $selectedRate% ▼") }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                DropdownMenuItem(text = { Text("$selectedRate%") }, onClick = { expanded = false })
            }
        }
        OutlinedTextField(value = topUpStr, onValueChange = { topUpStr = it }, label = { Text("Ежемесячное пополнение (необязательно)") })
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { navController.popBackStack() }) { Text("Назад") }
            Button(onClick = {
                vm.setRate(selectedRate)
                vm.setTopUp(topUpStr.toDoubleOrNull() ?: 0.0)
                navController.navigate("result")
            }) { Text("Рассчитать") }
        }
    }
}