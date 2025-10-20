package com.example.partnerkin.presentation

import com.example.partnerkin.domain.models.ConferenceModel

data class ConferencesState(
    val conferencesByMonth: Map<Pair<Int, Int>, List<ConferenceModel>> = emptyMap(),
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed interface ConferencesEvent {
    object LoadConferences : ConferencesEvent
    object Refresh : ConferencesEvent
    object TapOnConference : ConferencesEvent
}
