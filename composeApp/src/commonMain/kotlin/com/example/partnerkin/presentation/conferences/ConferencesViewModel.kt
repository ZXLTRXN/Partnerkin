package com.example.partnerkin.presentation.conferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partnerkin.domain.GetConferencesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.number

class ConferencesViewModel(
    private val getConferences: GetConferencesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ConferencesState())
    val uiState = _uiState.asStateFlow()

    init {
        onEvent(ConferencesEvent.LoadConferences)
    }

    fun onEvent(event: ConferencesEvent) {
        when (event) {
            is ConferencesEvent.LoadConferences -> loadConferences()
            is ConferencesEvent.Refresh -> loadConferences(isRefresh = true)
            is ConferencesEvent.TapOnConference -> loadConferences(isRefresh = true)
        }
    }

    private fun loadConferences(isRefresh: Boolean = false) {
        if (_uiState.value.isLoading) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val result = getConferences.invoke()

            result.onSuccess { result ->
                val conferencesByMonth = result.groupBy { conference ->
                    Pair(conference.startDate.year, conference.startDate.month.number)
                }
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        conferencesByMonth = conferencesByMonth,
                        error = null
                    )
                }
            }.onFailure { err ->
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        error = err.message
                    )
                }
            }
        }
    }
}