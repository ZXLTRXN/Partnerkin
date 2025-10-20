package com.example.partnerkin.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ConferencesResponseDTO(
    val error: String? = null,
    val data: Data
) {
    @Serializable
    data class Data(
        val result: List<ConferenceDataDTO>
    )
}
