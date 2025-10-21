package com.example.partnerkin.presentation.conferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import com.example.partnerkin.domain.models.ConferenceModel
import com.example.partnerkin.domain.models.DomainStatus
import com.example.partnerkin.presentation.conferences.components.ConferenceItem
import com.example.partnerkin.presentation.conferences.components.ConferenceMonthHeader
import com.example.partnerkin.ui.theme.AppTheme
import com.example.partnerkin.util.Mocks
import com.example.partnerkin.util.getLocalizedMonthName
import kotlinx.datetime.Month
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import partnerkin.composeapp.generated.resources.Res
import partnerkin.composeapp.generated.resources.compose_multiplatform


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
            val conferenceGroups = state.conferencesByMonth.entries.toList()
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                conferenceGroups.forEachIndexed { index, group ->
                    val (datePair, conferences) = group
                    val (year, monthNumber) = datePair
                    val month = Month(monthNumber)
                    val monthName = getLocalizedMonthName(month)

                    stickyHeader(
                        key = datePair,
                        "header"

                    ) {
                        val headerModifier = Modifier.padding(16.dp)
                        ConferenceMonthHeader(
                            "$monthName, $year",
                            modifier = if (index > 0) headerModifier.padding(top = 36.dp)
                            else headerModifier
                        )
                    }

                    items(
                        items = conferences,
                        key = { conference -> conference.id },
                        contentType = { "conference" }
                    ) { conference ->
                        ConferenceItem(
                            title = conference.name,
                            image = painterResource(Res.drawable.compose_multiplatform),
                            dateStart = conference.startDate,
                            dateEnd = conference.endDate,
                            tags = conference.categories.map { it.name },
                            location = "${conference.city}, ${conference.country}",
                            isCancelled = conference.status == DomainStatus.CANCELED,
                            modifier = Modifier
                        )
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
        Pair(2023, 1) to Mocks.conferencesExample,
        Pair(2024, 2) to Mocks.conferencesExample
    )
    AppTheme {
        ConferencesScreenStateless(
            state = ConferencesState(conferencesByMonth = testData, isLoading = false),
            pullRefreshState = rememberPullRefreshState(false, {})
        )
    }

}