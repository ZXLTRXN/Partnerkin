package com.example.partnerkin.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.partnerkin.presentation.conferences.ConferencesScreen
import com.example.partnerkin.ui.theme.AppTheme


@Composable
fun App() {
    AppTheme {
        Scaffold() { innerPadding -> //             .safeContentPadding() // fixme
            ConferencesScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}