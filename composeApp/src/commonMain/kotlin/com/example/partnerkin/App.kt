package com.example.partnerkin

import androidx.compose.runtime.Composable
import com.example.partnerkin.presentation.conferences.ConferencesScreen
import com.example.partnerkin.ui.theme.AppTheme


@Composable
fun App() {
    AppTheme {
        ConferencesScreen()
    }
}