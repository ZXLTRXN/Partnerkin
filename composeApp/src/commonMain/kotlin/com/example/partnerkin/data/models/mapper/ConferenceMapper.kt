package com.example.partnerkin.data.models.mapper

import com.example.partnerkin.data.models.ConferenceDTO
import com.example.partnerkin.data.models.ConferencesResponseDTO
import com.example.partnerkin.domain.models.ConferenceModel
import kotlinx.datetime.LocalDate

fun ConferencesResponseDTO.toDomain(): List<ConferenceModel> {
    return this.data.result.map { it.toDomain() }
}

fun ConferenceDTO.toDomain(): ConferenceModel {
    return ConferenceModel(
        id = this.conference.id,
        name = this.conference.name,
        format = this.conference.format,
        status = this.conference.status,
        statusTitle = this.conference.statusTitle,
        url = this.conference.url,
        image = this.conference.image?.toDomain(),
        rating = this.conference.rating,
        startDate = LocalDate.parse(this.conference.startDate),
        endDate = LocalDate.parse(this.conference.endDate),
        oneday = this.conference.oneday,
        customDate = this.conference.customDate,
        countryId = this.conference.countryId,
        country = this.conference.country,
        cityId = this.conference.cityId,
        city = this.conference.city,
        categories = this.conference.categories.map { it.toDomain() },
        typeId = this.conference.typeId,
        type = this.conference.type.toDomain()
    )
}

fun ConferenceDTO.ImageDTO.toDomain(): ConferenceModel.ImageModel {
    return ConferenceModel.ImageModel(
        id = this.id,
        url = this.url,
        preview = this.preview,
        placeholderColor = this.placeholderColor
    )
}

fun ConferenceDTO.CategoryDTO.toDomain(): ConferenceModel.CategoryModel {
    return ConferenceModel.CategoryModel(
        id = this.id,
        name = this.name,
        url = this.url
    )
}

fun ConferenceDTO.TypeDTO.toDomain(): ConferenceModel.TypeModel {
    return ConferenceModel.TypeModel(
        id = this.id,
        name = this.name
    )
}
