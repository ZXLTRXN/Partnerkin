package com.example.partnerkin.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.partnerkin.ui.LocalSnackbarHostState
import com.example.partnerkin.ui.components.CategoryLabel
import com.example.partnerkin.ui.components.RemoteImageWithPlaceholder
import com.example.partnerkin.ui.theme.AppTheme
import com.example.partnerkin.util.Mocks
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import partnerkin.composeapp.generated.resources.Res
import partnerkin.composeapp.generated.resources.conference_about_event
import partnerkin.composeapp.generated.resources.conference_button_register
import partnerkin.composeapp.generated.resources.icon_pin
import partnerkin.composeapp.generated.resources.icon_schedule

@Composable
fun ConferenceDetailsScreen(
    viewModel: ConferenceDetailsViewModel = koinInject(),
    modifier: Modifier = Modifier,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHost = LocalSnackbarHostState.current

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is ConferenceDetailsEffect.ShowSnackbar -> {
                    snackbarHost.showSnackbar(getString(effect.message))
                }
            }
        }
    }

    ConferenceDetailsScreenStateless(
        state = state,
        onRegisterClick = { viewModel.onEvent(ConferenceDetailsEvent.TapOnRegister) },
        modifier = modifier
    )
}

@Composable
fun ConferenceDetailsScreenStateless(
    state: ConferenceDetailsState = ConferenceDetailsState(),
    onRegisterClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    when {
        state.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(state.error))
            }
        }

        state.conference != null -> {
            val scrollState = rememberScrollState()
            val conference = state.conference
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(scrollState)
            ) {
                Spacer(Modifier.height(12.dp))
                Text(
                    text = conference.type.name,
                    fontSize = 14.sp,
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = conference.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(Modifier.height(20.dp))

                RemoteImageWithPlaceholder(
                    imageUrl = conference.image?.url,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .fillMaxWidth()
                        ,
                    contentScale = ContentScale.Crop
                )

                Spacer(Modifier.height(20.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    maxLines = 2
                ) {
                    conference.categories.forEach { category ->
                        CategoryLabel(category.name, backgroundColor = MaterialTheme.colorScheme.surface)
                    }
                }

                Spacer(Modifier.height(20.dp))

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            painterResource(Res.drawable.icon_schedule),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = conference.readablePeriod,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            painterResource(Res.drawable.icon_pin),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "${conference.city}, ${conference.country}",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )
                    }
                }


                Spacer(Modifier.height(28.dp))

                Button(
                    onClick = onRegisterClick,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(Res.string.conference_button_register),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(vertical = 11.dp, horizontal = 16.dp)
                    )
                }

                Spacer(Modifier.height(24.dp))

                DetailsChapter(
                    title = stringResource(Res.string.conference_about_event),
                ) {
                    Text(
                        text = conference.about,
                        fontSize = 15.sp,
                    )
                }
            }
        }
    }
}

@Composable
fun DetailsChapter(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
        )
        content()
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ConferenceItemPreview() {
    AppTheme {
        val item = Mocks.sigmaAfrica2025
        ConferenceDetailsScreenStateless(state = ConferenceDetailsState(conference = item))
    }
}
