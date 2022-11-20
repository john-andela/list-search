package com.johnseremba.stubhub.ui

import com.johnseremba.stubhub.model.Event

sealed class EventUiContract {
    sealed class UiEvent {
        object LoadEvents : UiEvent()
    }

    data class UiState(
        val isLoading: Boolean = false,
        val eventList: List<Event> = emptyList()
    )
}
