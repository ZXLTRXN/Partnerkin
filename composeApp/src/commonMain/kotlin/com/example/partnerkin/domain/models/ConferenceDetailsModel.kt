package com.example.partnerkin.domain.models

import kotlinx.datetime.LocalDate

data class ConferenceDetailsModel(
    val id: Int,
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
)