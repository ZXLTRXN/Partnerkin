package com.example.partnerkin.util

import kotlinx.datetime.Month
import platform.Foundation.NSCalendar
import platform.Foundation.NSDateComponents
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale

actual fun getLocalizedMonthName(
    month: Month,
    format: MonthFormat,
): String? {
    val formatter = NSDateFormatter()

    return try {
        when (format) {
            MonthFormat.FULL_RUSSIAN -> {
                formatter.setLocale(NSLocale(localeIdentifier = "ru_RU"))

                formatter.monthSymbols[month.ordinal] as String
            }
            MonthFormat.SHORT_ENG -> {
                formatter.setLocale(NSLocale(localeIdentifier = "en"))
                val result = formatter.shortMonthSymbols[month.ordinal] as String
                result.uppercase()
            }

            MonthFormat.FULL_RUSSIAN_SIMPLE -> {
                val monthsRu = listOf(
                    "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                    "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
                )
                return monthsRu[month.ordinal]
            }
        }
    } catch (ex: Exception) {
        // logs
        null
    }
}
