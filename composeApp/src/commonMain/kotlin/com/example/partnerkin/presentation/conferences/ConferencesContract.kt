package com.example.partnerkin.presentation.conferences

import androidx.compose.runtime.Immutable
import org.jetbrains.compose.resources.StringResource

@Immutable
data class ConferencesState(
    val conferencesByMonth: Map<MonthGroup, List<ConferenceItemData>> = emptyMap(),
    val isLoading: Boolean = false,
    val error: StringResource? = null
) {
    val isEmpty: Boolean = conferencesByMonth.isEmpty()
}

sealed interface ConferencesEvent {
    data object LoadConferences : ConferencesEvent
    data object Refresh : ConferencesEvent
    data object TapOnConference : ConferencesEvent
}
