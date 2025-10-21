package com.example.partnerkin.presentation.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.partnerkin.ui.theme.AppTheme
import com.example.partnerkin.util.Mocks
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.partnerkin.ui.components.CategoryLabel
import com.example.partnerkin.ui.components.RemoteImageWithPlaceholder
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun ConferenceDetailsScreen(
    viewModel: ConferenceDetailsViewModel = koinInject(),
    modifier: Modifier = Modifier,
) {
    val state by viewModel.uiState.collectAsState()

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
            val conference = state.conference
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 16.dp)
//                    .scrollable()
                ,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(Modifier.height(12.dp))
                Text(
                    text = conference.type.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = conference.name,
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(Modifier.height(12.dp))
                RemoteImageWithPlaceholder(
                    imageUrl = conference.image?.url ?: "",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(16.dp)),
                )

                Spacer(Modifier.height(12.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    conference.categories.forEach { category ->
                        CategoryLabel(category.name)
                    }
                }

                Spacer(Modifier.height(12.dp))

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
//                            Icon(
//                                painterResource("drawable/compose-multiplatform.xml"),
//                                contentDescription = null,
//                                tint = MaterialTheme.colorScheme.primary
//                            )
                        Text(
                            text = conference.customDate ?: "${conference.startDate} - ${conference.endDate}",
                            fontSize = 15.sp
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
//                            Icon(
//                                painterResource("drawable/compose-multiplatform.xml"),
//                                contentDescription = null,
//                                tint = MaterialTheme.colorScheme.primary
//                            )
                        Text(text = "${conference.city}, ${conference.country}", fontSize = 15.sp)
                    }
                }


                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = onRegisterClick,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Регистрация", color = MaterialTheme.colorScheme.onPrimary)
                }

                Spacer(Modifier.height(24.dp))

                Text(
                    "Связанные события",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                )

//                RelatedEventItem(
//                    title = when (index) {
//                        0 -> "ЛАС-ВЕГАС ЯНВ '24"
//                        1 -> "ЛАС-ВЕГАС ЯНВ '24"
//                        else -> "ЛАС-ВЕГАС ЯНВ '23"
//                    },
//                    rating = if (index == 2) "8.3" else "5",
//                    isNew = index == 0
//                )


                Spacer(Modifier.height(16.dp))
                Text(
                    "О событии",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = conference.about,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 20.sp
                )
                Spacer(Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun RelatedEventItem(title: String, rating: String, isNew: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (isNew) {
                    Surface(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            "New",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                Text(title, fontSize = 15.sp, fontWeight = FontWeight.Medium)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
//                Icon(
//                    painterResource("drawable/compose-multiplatform.xml"),
//                    contentDescription = null,
//                    tint = MaterialTheme.colorScheme.tertiary
//                )
                Text(rating, fontWeight = FontWeight.SemiBold)
            }
        }
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
