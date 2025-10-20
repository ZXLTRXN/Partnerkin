package com.example.partnerkin.domain.models

data class ImageModel(
    val id: String,
    val url: String,
    val preview: String,
    val placeholderColor: String? = null
)