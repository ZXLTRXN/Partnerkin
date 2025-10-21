package com.example.partnerkin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryLabel(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(60.dp)
            )
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}