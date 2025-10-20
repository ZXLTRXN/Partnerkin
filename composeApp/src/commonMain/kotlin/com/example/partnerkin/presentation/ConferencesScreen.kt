package com.example.partnerkin.presentation

import ConferencesViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.partnerkin.presentation.components.ConferenceItem
import com.example.partnerkin.presentation.components.ConferenceMonthHeader
import com.example.partnerkin.util.getLocalizedMonthName
import kotlinx.datetime.Month
import org.koin.compose.koinInject


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ConferencesScreen(viewModel: ConferencesViewModel = koinInject()) {
    val state by viewModel.uiState.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = { viewModel.onEvent(ConferencesEvent.Refresh) }
    )

    Box(
        modifier = Modifier.fillMaxSize()
            .safeContentPadding()
            .pullRefresh(pullRefreshState)
    ) {
        if (state.isLoading && state.conferencesByMonth.isEmpty()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {

        }

        PullRefreshIndicator(
            refreshing = state.isLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}
