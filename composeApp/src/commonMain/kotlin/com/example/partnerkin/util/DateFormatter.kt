package com.example.partnerkin.util

import kotlinx.datetime.Month

/**
 * Format for month names.
 */
expect fun getLocalizedMonthName(
    month: Month,
    format: MonthFormat,
): String?
