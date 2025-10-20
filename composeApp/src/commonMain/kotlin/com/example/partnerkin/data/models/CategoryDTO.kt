package com.example.partnerkin.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDTO(
    val id: Int,
    val name: String,
    val url: String
)