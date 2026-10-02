package com.shiwa.onearun.ui.screens.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeUiState(
    val success: Boolean
)


class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState(success = false))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    fun onEnterRaceClick(){
        _uiState.value = HomeUiState(success = true)
    }
    fun onStartRaceClick(){
        _uiState.value = HomeUiState(success = true)
    }

}