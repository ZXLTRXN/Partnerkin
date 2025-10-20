package com.example.partnerkin.presentation.conferences

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.partnerkin.domain.models.ConferenceModel
import com.example.partnerkin.util.Mocks
import com.example.partnerkin.presentation.conferences.components.ConferenceItem
import com.example.partnerkin.presentation.conferences.components.ConferenceMonthHeader
import com.example.partnerkin.ui.theme.AppTheme
import com.example.partnerkin.util.getLocalizedMonthName
import kotlinx.datetime.Month
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ConferencesScreen(
    viewModel: ConferencesViewModel = koinInject(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = { viewModel.onEvent(ConferencesEvent.Refresh) }
    )

    ConferencesScreenStateless(state, pullRefreshState, modifier)

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ConferencesScreenStateless(
    state: ConferencesState,
    pullRefreshState: PullRefreshState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        if (!state.isLoading) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                state.conferencesByMonth.forEach { (datePair, conferences) ->
                    val (year, monthNumber) = datePair
                    val month = Month(monthNumber)
                    val monthName = getLocalizedMonthName(month)

                    item {
                        ConferenceMonthHeader("$monthName, $year")
                    }

                    items(conferences.size) { index ->
                        ConferenceItem(conference = conferences[index])
                    }

                }
            }
        }

        PullRefreshIndicator(
            refreshing = state.isLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(
    showBackground = true
)
@Composable
fun ConferencesScreenStatelessPreview() {
    val testData = mapOf<Pair<Int, Int>, List<ConferenceModel>>(
        Pair(2023, 1) to Mocks.conferences
    )
    AppTheme {
        ConferencesScreenStateless(
            state = ConferencesState(conferencesByMonth = testData, isLoading = false),
            pullRefreshState = rememberPullRefreshState(false, {})
        )
    }

}

