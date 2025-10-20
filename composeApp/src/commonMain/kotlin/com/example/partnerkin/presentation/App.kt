package com.example.partnerkin.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.partnerkin.presentation.conferences.ConferencesScreen
import com.example.partnerkin.ui.theme.AppTheme


@Composable
fun App() {
    AppTheme {
        Scaffold() { //             .safeContentPadding() // fixme
            ConferencesScreen()
        }
    }
}