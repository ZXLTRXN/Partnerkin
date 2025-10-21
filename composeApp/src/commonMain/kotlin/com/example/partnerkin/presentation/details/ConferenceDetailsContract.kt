package com.example.partnerkin.presentation.details

import com.example.partnerkin.domain.models.ConferenceDetailsModel
import org.jetbrains.compose.resources.StringResource


data class ConferenceDetailsState(
    val conference: ConferenceDetailsModel? = null,
    val isLoading: Boolean = false,
    val error: StringResource? = null
)

sealed interface ConferenceDetailsEvent {
    data object LoadConference : ConferenceDetailsEvent
    data object TapOnRegister : ConferenceDetailsEvent
}

sealed interface ConferenceDetailsEffect {
    data class ShowSnackbar(val message: StringResource) : ConferenceDetailsEffect
}