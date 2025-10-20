package com.example.partnerkin.data.models

import kotlinx.serialization.Serializable

@Serializable
data class TypeDTO(
    val id: Int,
    val name: String
)