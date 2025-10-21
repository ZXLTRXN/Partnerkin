package com.example.partnerkin.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.partnerkin.presentation.conferences.ConferencesScreen
import com.example.partnerkin.presentation.details.ConferenceDetailsScreen
import com.example.partnerkin.presentation.details.ConferenceDetailsViewModel
import com.example.partnerkin.presentation.navigation.Screen
import com.example.partnerkin.ui.LocalSnackbarHostState
import com.example.partnerkin.ui.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    AppTheme {
        var screen by rememberSaveable(stateSaver = Screen.Saver) { mutableStateOf<Screen>(Screen.Conferences) }
        val snackbarHostState = remember { SnackbarHostState() }
        CompositionLocalProvider(
            LocalSnackbarHostState provides snackbarHostState
        ) {
            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) },
                topBar = {
                    TopAppBar(
                        title = { screen.title?.let { Text(stringResource(it)) } },
                        navigationIcon = {
                            if (screen !is Screen.Conferences) {
                                IconButton(onClick = { screen = Screen.Conferences }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                        }
                    )
                }
            ) { innerPadding ->
                when (val currentScreen = screen) {
                    is Screen.Conferences -> {
                        ConferencesScreen(
                            onConferenceClick = { conferenceId ->
                                screen = Screen.ConferenceDetails(conferenceId)
                            },
                            modifier = Modifier.padding(innerPadding)
                        )
                    }

                    is Screen.ConferenceDetails -> {
                        val viewModel: ConferenceDetailsViewModel = koinInject(
                            parameters = { parametersOf(currentScreen.conferenceId) }
                        )
                        ConferenceDetailsScreen(
                            viewModel = viewModel,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}