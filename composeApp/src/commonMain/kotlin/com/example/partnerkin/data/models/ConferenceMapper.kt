package com.example.partnerkin.data.models

import com.example.partnerkin.domain.models.CategoryModel
import com.example.partnerkin.domain.models.ConferenceDetailsModel
import com.example.partnerkin.domain.models.ConferenceModel
import com.example.partnerkin.domain.models.ImageModel
import com.example.partnerkin.domain.models.TypeModel
import kotlinx.datetime.LocalDate

fun ConferencesResponseDTO.toDomain(): List<ConferenceModel> {
    return this.data.result.map { it.toDomain() }
}

fun ConferenceDataDTO.toDomain(): ConferenceModel {
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

fun ImageDTO.toDomain(): ImageModel {
    return ImageModel(
        id = this.id,
        url = this.url,
        preview = this.preview,
        placeholderColor = this.placeholderColor
    )
}

fun CategoryDTO.toDomain(): CategoryModel {
    return CategoryModel(
        id = this.id,
        name = this.name,
        url = this.url
    )
}

fun TypeDTO.toDomain(): TypeModel {
    return TypeModel(
        id = this.id,
        name = this.name
    )
}

fun ConferenceDetailsDTO.toDomain(): ConferenceDetailsModel {
    return ConferenceDetailsModel(
        id = this.id,
        name = this.name,
        format = this.format,
        status = this.status,
        statusTitle = this.statusTitle,
        url = this.url,
        image = this.image?.toDomain(),
        rating = this.rating,
        startDate = LocalDate.parse(this.startDate),
        endDate = LocalDate.parse(this.endDate),
        oneday = this.oneday,
        customDate = this.customDate,
        countryId = this.countryId,
        country = this.country,
        cityId = this.cityId,
        city = this.city,
        categories = this.categories.map { it.toDomain() },
        typeId = this.typeId,
        type = this.type.toDomain(),
        registerUrl = this.registerUrl,
        about = this.about
    )
}
