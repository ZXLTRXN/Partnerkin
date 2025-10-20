import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.partnerkin.presentation.ConferencesEvent
import com.example.partnerkin.presentation.ConferencesState
import com.example.partnerkin.domain.ConferenceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ConferencesViewModel(private val repository: ConferenceRepository): ViewModel() {

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

            val result = repository.getConferences()

            result.onSuccess { result ->
                val conferencesByMonth = result.groupBy { conference ->
                    Pair(conference.startDate.year, conference.startDate.monthNumber)
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