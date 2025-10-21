package com.example.partnerkin.presentation.conferences.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.partnerkin.presentation.conferences.ConferenceItemData
import com.example.partnerkin.presentation.conferences.toItemData
import com.example.partnerkin.ui.components.CategoryLabel
import com.example.partnerkin.ui.components.RemoteImageWithPlaceholder
import com.example.partnerkin.ui.theme.AppTheme
import com.example.partnerkin.ui.theme.LightOnOpaquePrimary
import com.example.partnerkin.util.Mocks
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import partnerkin.composeapp.generated.resources.Res
import partnerkin.composeapp.generated.resources.conferences_list_date_separator
import partnerkin.composeapp.generated.resources.conferences_list_status_canceled

@Composable
fun ConferenceItem(
    data: ConferenceItemData,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val backgroundColor = if (!data.isCancelled)
        MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.errorContainer

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier.padding(
                bottom = 24.dp,
                start = 16.dp,
                end = 16.dp
            )
        ) {
            if (data.isCancelled) {
                CanceledLabel(
                    Modifier.padding(top = 10.dp)
                )
            }
            Spacer(Modifier.height(24.dp))

            Text(
                data.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(20.dp))

            val imageContainerColor = if (data.isCancelled)
                MaterialTheme.colorScheme.error.copy(0.06f)
            else MaterialTheme.colorScheme.primary.copy(0.04f)



            ImageDateContainer(
                imageUrl = data.imageUrl,
                backgroundColor = imageContainerColor,
                startDayMonth = Pair(
                    data.startDate.day.toString(),
                    data.startMonthNameShortOrNull ?: ""
                ),
                endDayMonth = Pair(
                    data.endDate.day.toString(),
                    data.endMonthNameShortOrNull ?: ""
                )
            )

            Spacer(Modifier.height(24.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                maxLines = 2
            ) {
                data.categories.forEach { category ->
                    CategoryLabel(category.name)
                }
            }

            Spacer(Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    data.location,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun ImageDateContainer(
    imageUrl: String?,
    backgroundColor: Color,
    startDayMonth: Pair<String, String>,
    endDayMonth: Pair<String, String>,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = backgroundColor
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RemoteImageWithPlaceholder(
                imageUrl = imageUrl,
                modifier = Modifier
                    .size(width = 156.dp, height = 104.dp),
                contentScale = ContentScale.Crop
            )

            TwoLinesDate(
                startDayMonth = startDayMonth,
                endDayMonth = endDayMonth,
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 11.dp)
            )
        }
    }

}

@Composable
fun TwoLinesDate(
    startDayMonth: Pair<String, String>,
    endDayMonth: Pair<String, String>,
    modifier: Modifier = Modifier
) {
    val (startDay, startMonth) = startDayMonth

    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                startDay.padStart(2, '0'),
                fontSize = 40.sp,
                fontWeight = FontWeight.Light
            )

            Text(
                text = startMonth,
                fontSize = 12.sp, color = LightOnOpaquePrimary.copy(0.6f)
            )
        }
        if (startDayMonth != endDayMonth) {
            val (endDay, endMonth) = endDayMonth
            Text(
                stringResource(Res.string.conferences_list_date_separator),
                fontSize = 40.sp,
                fontWeight = FontWeight.Light,
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    endDay.padStart(2, '0'),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Light,
                )
                Text(
                    text = endMonth,
                    fontSize = 12.sp, color = LightOnOpaquePrimary.copy(0.6f)
                )
            }
        }
    }

}

@Composable
fun CanceledLabel(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(60.dp)
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.error,
                shape = RoundedCornerShape(60.dp)
            )
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ErrorOutline,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.size(14.dp)
        )
        Spacer(Modifier.width(4.dp))
        Text(
            stringResource(Res.string.conferences_list_status_canceled),
            color = MaterialTheme.colorScheme.error,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
    Spacer(Modifier.height(8.dp))
}


@Preview(
    showBackground = true
)
@Composable
fun ConferenceItemPreview() {
    AppTheme {
        val item = Mocks.normalConference.toItemData()
        ConferenceItem(
            data = item,
            onClick = {}
        )
    }
}