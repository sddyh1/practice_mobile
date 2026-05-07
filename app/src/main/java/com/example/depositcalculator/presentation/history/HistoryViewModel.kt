package com.example.depositcalculator.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.depositcalculator.domain.repository.DepositRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: DepositRepository
) : ViewModel() {
    val calculations = repository.getAllCalculations()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}