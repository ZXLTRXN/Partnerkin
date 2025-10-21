package com.example.partnerkin.presentation.conferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partnerkin.domain.GetConferencesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.number
import org.jetbrains.compose.resources.StringResource
import partnerkin.composeapp.generated.resources.Res
import partnerkin.composeapp.generated.resources.error_unknown

class ConferencesViewModel(
    private val getConferences: GetConferencesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ConferencesState())
    val uiState = _uiState.asStateFlow()

    init {
        onEvent(ConferencesEvent.LoadConferences)
    }

    fun onEvent(event: ConferencesEvent) {
        when (event) {
            is ConferencesEvent.LoadConferences -> loadConferences()
            is ConferencesEvent.Refresh -> loadConferences()
            is ConferencesEvent.TapOnConference -> loadConferences()
        }
    }

    private fun loadConferences() {
        if (_uiState.value.isLoading) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val result = getConferences.invoke()

            result.onSuccess { result ->
                val conferencesByMonth = result
                    .map { conference ->
                        conference.toItemData()
                    }
                    .groupBy { conference ->
                        MonthGroup(conference.startDate.year, conference.startDate.month.number)
                    }

                val conferencesSortedByMonth: LinkedHashMap<MonthGroup, List<ConferenceItemData>> =
                    conferencesByMonth.entries
                        .sortedWith(compareBy({ it.key.year }, { it.key.month }))
                        .associateTo(LinkedHashMap()) { it.key to it.value }

                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        conferencesByMonth = conferencesSortedByMonth,
                        error = null
                    )
                }

            }.onFailure { err ->
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        error = mapError(err),
                        conferencesByMonth = emptyMap()
                    )
                }
            }
        }
    }

    fun mapError(exception: Throwable): StringResource {
        return Res.string.error_unknown
    }
}