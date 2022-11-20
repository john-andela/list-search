package com.johnseremba.stubhub.ui

import androidx.lifecycle.ViewModel
import com.johnseremba.stubhub.ui.EventUiContract.UiEvent
import com.johnseremba.stubhub.ui.EventUiContract.UiEvent.LoadEvents
import com.johnseremba.stubhub.ui.EventUiContract.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun sendEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is LoadEvents -> loadEventsFromFile()
        }
    }

    private fun setUiState(reducer: UiState.() -> UiState) {
        val newState = _uiState.value.reducer()
        _uiState.value = newState
    }

    private fun loadEventsFromFile() {
        setUiState {
            copy(isLoading = false)
        }
    }
}
