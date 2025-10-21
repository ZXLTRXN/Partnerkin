package com.example.partnerkin.data.models

import com.example.partnerkin.data.models.utils.LocalDateSerializer
import com.example.partnerkin.data.models.utils.StatusSerializer
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConferenceDetailsResponseDTO(
    val error: String? = null,
    val data: ConferenceDetailsDTO
)

@Serializable
data class ConferenceDetailsDTO(
    val id: Long,
    val name: String,
    val format: String,
    @Serializable(with = StatusSerializer::class)
    val status: Status,
    @SerialName("status_title")
    val statusTitle: String,
    val url: String,
    val image: ImageDTO? = null,
    val rating: Double? = null,
    @SerialName("start_date")
    @Serializable(with = LocalDateSerializer::class)
    val startDate: LocalDate,
    @SerialName("end_date")
    @Serializable(with = LocalDateSerializer::class)
    val endDate: LocalDate,
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
    val type: TypeDTO,
    @SerialName("register_url")
    val registerUrl: String,
    val about: String
)