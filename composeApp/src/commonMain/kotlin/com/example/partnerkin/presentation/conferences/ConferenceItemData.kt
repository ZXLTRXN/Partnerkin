package com.example.partnerkin.presentation.conferences

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.example.partnerkin.domain.models.CategoryModel
import com.example.partnerkin.domain.models.ConferenceModel
import com.example.partnerkin.domain.models.DomainStatus
import com.example.partnerkin.util.MonthFormat
import com.example.partnerkin.util.getLocalizedMonthName
import kotlinx.datetime.LocalDate

@Immutable
data class ConferenceItemData(
    val id: Long,
    val title: String,
    val imageUrl: String?,
    val imagePlaceholderColor: Color?,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val categories: List<CategoryModel>,
    val location: String,
    val status: DomainStatus,
    val isCancelled: Boolean,
) {
    val startMonthNameShortOrNull: String? =
        getLocalizedMonthName(startDate.month, MonthFormat.SHORT_ENG)
    val endMonthNameShortOrNull: String? =
        getLocalizedMonthName(endDate.month, MonthFormat.SHORT_ENG)
}

fun ConferenceModel.toItemData(): ConferenceItemData {
    return ConferenceItemData(
        id = id,
        title = name,
        imageUrl = image?.url,
        imagePlaceholderColor = null,
        startDate = startDate,
        endDate = endDate,
        categories = categories,
        location = "$city, $country",
        status = status,
        isCancelled = isCanceled
    )
}