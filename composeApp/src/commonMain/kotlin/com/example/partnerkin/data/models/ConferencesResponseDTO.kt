package com.example.partnerkin.data.models

import kotlinx.serialization.Serializable


@Serializable
data class ConferencesResponseDTO(
    val error: String? = null,
    val data: Data
) {
    @Serializable
    data class Data(
//        val counts: Int,
//        val pagination: Pagination,
        val result: List<ConferenceDTO>
    )

//    @Serializable
//    data class Pagination(
//        @SerialName("page_size")
//        val pageSize: Int,
//        @SerialName("current_page")
//        val currentPage: Int
//    )
}
