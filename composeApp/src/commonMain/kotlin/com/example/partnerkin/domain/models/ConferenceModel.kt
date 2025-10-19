package com.example.partnerkin.domain.models

import kotlinx.datetime.LocalDate

data class ConferenceModel(
    val id: Int,
    val name: String,
    val format: String,
    val status: String,
    val statusTitle: String,
    val url: String,
    val image: ImageModel?,
    val rating: Double? = null,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val oneday: Int,
    val customDate: String? = null,
    val countryId: Int,
    val country: String,
    val cityId: Int,
    val city: String,
    val categories: List<CategoryModel>,
    val typeId: Int,
    val type: TypeModel
) {
    data class ImageModel(
        val id: String,
        val url: String,
        val preview: String,
        val placeholderColor: String? = null
    )

    data class CategoryModel(
        val id: Int,
        val name: String,
        val url: String
    )

    data class TypeModel(
        val id: Int,
        val name: String
    )
}