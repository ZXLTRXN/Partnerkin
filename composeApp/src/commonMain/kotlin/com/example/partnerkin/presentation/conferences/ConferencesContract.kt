package com.example.partnerkin.presentation.conferences


data class ConferencesState(
    val conferencesByMonth: Map<MonthGroup, List<ConferenceItemData>> = emptyMap(),
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed interface ConferencesEvent {
    object LoadConferences : ConferencesEvent
    object Refresh : ConferencesEvent
    object TapOnConference : ConferencesEvent
}
