package com.example.partnerkin.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partnerkin.domain.GetConferenceDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource
import partnerkin.composeapp.generated.resources.Res
import partnerkin.composeapp.generated.resources.error_unknown

class ConferenceDetailsViewModel(
    private val conferenceId: Long,
    private val getConferenceDetailsUseCase: GetConferenceDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ConferenceDetailsState())
    val uiState = _uiState.asStateFlow()

    init {
        onEvent(ConferenceDetailsEvent.LoadConference)
    }

    fun onEvent(event: ConferenceDetailsEvent) {
        when (event) {
            is ConferenceDetailsEvent.LoadConference -> loadConference()
            is ConferenceDetailsEvent.TapOnRegister -> { /* TODO */ }
        }
    }

    private fun loadConference() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val conference = getConferenceDetailsUseCase.invoke(conferenceId)

            conference.onSuccess { conference ->
                _uiState.update { it.copy(conference = conference, isLoading = false) }
            }.onFailure { ex ->
                _uiState.update { it.copy(error = mapError(ex), isLoading = false) }
            }
        }
    }

    fun mapError(exception: Throwable): StringResource {
        return Res.string.error_unknown
    }
}