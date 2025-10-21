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
import com.example.partnerkin.presentation.conferences.components.ConferenceItem
import com.example.partnerkin.presentation.conferences.components.ConferenceMonthHeader
import com.example.partnerkin.ui.theme.AppTheme
import com.example.partnerkin.util.Mocks
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ConferencesScreen(
    viewModel: ConferencesViewModel = koinInject(),
    onConferenceClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = { viewModel.onEvent(ConferencesEvent.Refresh) }
    )

    ConferencesScreenStateless(state, pullRefreshState, onConferenceClick, modifier)

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ConferencesScreenStateless(
    state: ConferencesState,
    pullRefreshState: PullRefreshState,
    onConferenceClick: (Long) -> Unit,
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
                    val (monthGroup, conferences) = group

                    stickyHeader(
                        key = "${monthGroup.year}-${monthGroup.month}",
                        "header"

                    ) {
                        val headerModifier = Modifier.padding(16.dp)
                        ConferenceMonthHeader(
                            "${monthGroup.monthNameOrNull ?: ""}, ${monthGroup.year}",
                            modifier = if (index > 0) headerModifier.padding(top = 36.dp)
                            else headerModifier
                        )
                    }

                    items(
                        items = conferences,
                        key = { conference -> conference.id },
                        contentType = { conference -> conference.status }
                    ) { conference ->
                        ConferenceItem(
                            data = conference,
                            modifier = Modifier,
                            onClick = { onConferenceClick(conference.id) }
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
    val testData = mapOf(
        MonthGroup(2023, 1) to Mocks.conferencesExample.map { it.toItemData() },
        MonthGroup(2024, 2) to Mocks.conferencesExample.map { it.toItemData() },
    )
    AppTheme {
        ConferencesScreenStateless(
            state = ConferencesState(conferencesByMonth = testData, isLoading = false),
            pullRefreshState = rememberPullRefreshState(false, {}),
            onConferenceClick = {}
        )
    }

}