package com.example.partnerkin.presentation.conferences


import com.example.partnerkin.util.MonthFormat
import com.example.partnerkin.util.getLocalizedMonthName
import kotlinx.datetime.Month

data class MonthGroup(
    val year: Int,
    val month: Int // starting 1
) {
    val monthNameOrNull: String? = getLocalizedMonthName(Month(month),
        MonthFormat.FULL_RUSSIAN_SIMPLE
    )
}