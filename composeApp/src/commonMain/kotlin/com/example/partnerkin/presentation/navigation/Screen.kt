package com.example.partnerkin.presentation.navigation

import androidx.compose.runtime.saveable.listSaver
import org.jetbrains.compose.resources.StringResource
import partnerkin.composeapp.generated.resources.Res
import partnerkin.composeapp.generated.resources.conferences_list_screen_title


sealed class Screen(val title: StringResource?) {
    data object Conferences : Screen(
        Res.string.conferences_list_screen_title
    )
    data class ConferenceDetails(val conferenceId: Long) : Screen(null)

    companion object {
        val Saver = listSaver<Screen, Any>(
            save = { screen ->
                when (screen) {
                    is Conferences -> listOf("conferences")
                    is ConferenceDetails -> listOf("details", screen.conferenceId)
                }
            },
            restore = { list ->
                when (list.firstOrNull()) {
                    "conferences" -> Conferences
                    "details" -> ConferenceDetails(list[1] as Long)
                    else -> error("Unknown screen type")
                }
            }
        )
    }
}
