package com.example.partnerkin.presentation.conferences.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.partnerkin.domain.models.DomainStatus
import com.example.partnerkin.ui.theme.AppTheme
import com.example.partnerkin.util.Mocks
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import partnerkin.composeapp.generated.resources.Res
import partnerkin.composeapp.generated.resources.compose_multiplatform

// TODO TEXT + limits

@Composable
fun ConferenceItem(
    title: String,
    image: Painter,
    dateStart: LocalDate,
    dateEnd: LocalDate,
    tags: List<String>,
    location: String,
    isCancelled: Boolean,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (!isCancelled)
        MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.errorContainer

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier.padding(
                bottom = 24.dp,
                start = 16.dp,
                end = 16.dp
            )
        ) {
            if (isCancelled) {
                CanceledLabel(
                    Modifier.padding(top = 10.dp)
                )
            }
            Spacer(Modifier.height(24.dp))

            Text(
                title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
            )

            Spacer(Modifier.height(20.dp))

            val imageContainerColor = if (isCancelled)
                MaterialTheme.colorScheme.error.copy(0.06f)
            else MaterialTheme.colorScheme.primary.copy(0.04f)

            ImageDateContainer(
                image = image,
                backgroundColor = imageContainerColor,
                dateStart = dateStart,
                dateEnd = dateEnd
            )

            Spacer(Modifier.height(24.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                maxLines = 2
            ) {
                tags.forEach { tag ->
                    CategoryLabel(tag)
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
                    location,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun CategoryLabel(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.background, // fixme check
                shape = RoundedCornerShape(60.dp)
            )
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun ImageDateContainer(
    image: Painter,
    backgroundColor: Color,
    dateStart: LocalDate,
    dateEnd: LocalDate,
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
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .size(width = 156.dp, height = 104.dp),
                contentScale = ContentScale.Crop
            )

            TwoLinesDate(
                leftDate = dateStart,
                rightDate = if (dateStart != dateEnd) dateEnd else null,
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 11.dp)
            )
        }
    }

}

@Composable
fun TwoLinesDate(
    leftDate: LocalDate,
    rightDate: LocalDate?,
    modifier: Modifier = Modifier
) {
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
                "${leftDate.day}",
                fontSize = 40.sp,
                fontWeight = FontWeight.Light
            )
            Text(leftDate.month.name.substring(0..2), fontSize = 12.sp, color = Color.Gray)
        }
        rightDate?.let {
            Text(
                "-",
                fontSize = 40.sp,
                fontWeight = FontWeight.Light
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "${rightDate.day}",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Light
                )
                Text(
                    leftDate.month.name.substring(0..2),
                    fontSize = 12.sp, color = Color.Gray
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
            "Отменено",
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
        val item = Mocks.canceledConference
        ConferenceItem(
            title = item.name,
            image = painterResource(Res.drawable.compose_multiplatform),
            dateStart = item.startDate,
            dateEnd = item.endDate,
            tags = item.categories.map { it.name },
            location = "${item.city}, ${item.country}",
            isCancelled = item.status == DomainStatus.CANCELED
        )
    }

}