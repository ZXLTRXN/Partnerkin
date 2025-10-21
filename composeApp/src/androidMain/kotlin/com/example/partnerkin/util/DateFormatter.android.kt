package com.example.partnerkin.util

import kotlinx.datetime.Month
import java.text.DateFormatSymbols
import java.util.Locale

actual fun getLocalizedMonthName(
    month: Month,
    format: MonthFormat,
): String? {
    return try {
        when (format) {
            MonthFormat.FULL_RUSSIAN -> {
                val locale = Locale("ru", "RU")
                val monthName = DateFormatSymbols(locale).months[month.ordinal]
                monthName.replaceFirstChar { it.titlecase(locale) }
            }

            MonthFormat.SHORT_ENG -> {
                val locale = Locale.ENGLISH
                val monthName = DateFormatSymbols(locale).shortMonths[month.ordinal]
                monthName.uppercase(locale)
            }
        }
    } catch (ex: Exception) {
        null
    }
}
