package com.example.partnerkin.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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