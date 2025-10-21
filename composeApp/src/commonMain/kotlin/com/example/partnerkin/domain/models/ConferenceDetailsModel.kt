package com.example.partnerkin.domain.models

import androidx.compose.runtime.Immutable
import com.example.partnerkin.util.MonthFormat
import com.example.partnerkin.util.getLocalizedMonthName
import kotlinx.datetime.LocalDate


@Immutable
data class ConferenceDetailsModel(
    val id: Long,
    val name: String,
    val format: String,
    val status: DomainStatus,
    val statusTitle: String,
    val url: String,
    val image: ImageModel?,
    val rating: Double?,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val oneday: Int,
    val customDate: String?,
    val countryId: Int,
    val country: String,
    val cityId: Int,
    val city: String,
    val categories: List<CategoryModel>,
    val typeId: Int,
    val type: TypeModel,
    val registerUrl: String,
    val about: String
) {

    val readableStartDate: String = getReadableDate(startDate)
    val readableEndDate: String = getReadableDate(endDate)

    val readablePeriod: String = if (startDate == endDate) {
        readableStartDate
    } else {
        "$readableStartDate - $readableEndDate"
    }

    private fun getReadableDate(date: LocalDate): String {
        val month = getLocalizedMonthName(date.month,
            MonthFormat.FULL_RUSSIAN
        ) ?: date.month.name
        return "${date.day} $month ${date.year}"
    }

}