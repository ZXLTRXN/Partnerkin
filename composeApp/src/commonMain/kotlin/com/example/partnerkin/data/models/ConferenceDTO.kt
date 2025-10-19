package com.example.partnerkin.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ConferenceDTO(
    val view_type: Int,
    val conference: Conference
) {
    @Serializable
    data class Conference(
        val id: Int,
        val name: String,
        val format: String,
        val status: String,
        val status_title: String,
        val url: String,
        val image: Image?,
        val rating: Double? = null,
        val start_date: String,
        val end_date: String,
        val oneday: Int,
        val custom_date: String? = null,
        val country_id: Int,
        val country: String,
        val city_id: Int,
        val city: String,
        val categories: List<Category>,
        val type_id: Int,
        val type: Type
    )

    @Serializable
    data class Image(
        val id: String,
        val url: String,
        val preview: String,
        val placeholder_color: String? = null,
        val width: Int,
        val height: Int
    )

    @Serializable
    data class Category(
        val id: Int,
        val name: String,
        val url: String
    )

    @Serializable
    data class Type(
        val id: Int,
        val name: String
    )
}