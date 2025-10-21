package com.example.partnerkin.presentation.details

import com.example.partnerkin.domain.models.ConferenceDetailsModel
import org.jetbrains.compose.resources.StringResource


data class ConferenceDetailsState(
    val conference: ConferenceDetailsModel? = null,
    val isLoading: Boolean = false,
    val error: StringResource? = null
)

sealed interface ConferenceDetailsEvent {
    object LoadConference : ConferenceDetailsEvent
    object TapOnRegister : ConferenceDetailsEvent
}