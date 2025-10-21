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
                DateFormatSymbols(locale).months[month.ordinal]
            }

            MonthFormat.SHORT_ENG -> {
                val locale = Locale.ENGLISH
                val monthName = DateFormatSymbols(locale).shortMonths[month.ordinal]
                monthName.uppercase(locale)
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
