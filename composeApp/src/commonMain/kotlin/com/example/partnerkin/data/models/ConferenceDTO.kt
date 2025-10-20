package com.example.partnerkin.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConferenceDataDTO(
    val conference: ConferenceDto
) {
    @Serializable
    data class ConferenceDto(
        val id: Int,
        val name: String,
        val format: String,
        val status: String,
        @SerialName("status_title")
        val statusTitle: String,
        val url: String,
        val image: ImageDTO?,
        val rating: Double? = null,
        @SerialName("start_date")
        val startDate: String,
        @SerialName("end_date")
        val endDate: String,
        val oneday: Int,
        @SerialName("custom_date")
        val customDate: String? = null,
        @SerialName("country_id")
        val countryId: Int,
        val country: String,
        @SerialName("city_id")
        val cityId: Int,
        val city: String,
        val categories: List<CategoryDTO>,
        @SerialName("type_id")
        val typeId: Int,
        val type: TypeDTO
    )

    @Serializable
    data class ImageDTO(
        val id: String,
        val url: String,
        val preview: String,
        @SerialName("placeholder_color")
        val placeholderColor: String? = null,
        val width: Int,
        val height: Int
    )

    @Serializable
    data class CategoryDTO(
        val id: Int,
        val name: String,
        val url: String
    )

    @Serializable
    data class TypeDTO(
        val id: Int,
        val name: String
    )
}