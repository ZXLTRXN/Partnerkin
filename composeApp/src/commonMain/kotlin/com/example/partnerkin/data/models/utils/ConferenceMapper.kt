package com.example.partnerkin.data.models.utils

import com.example.partnerkin.data.models.CategoryDTO
import com.example.partnerkin.data.models.ConferenceDataDTO
import com.example.partnerkin.data.models.ConferenceDetailsDTO
import com.example.partnerkin.data.models.ConferencesResponseDTO
import com.example.partnerkin.data.models.ImageDTO
import com.example.partnerkin.data.models.Status
import com.example.partnerkin.domain.models.CategoryModel
import com.example.partnerkin.domain.models.ConferenceDetailsModel
import com.example.partnerkin.domain.models.ConferenceModel
import com.example.partnerkin.domain.models.ImageModel
import com.example.partnerkin.data.models.TypeDTO
import com.example.partnerkin.domain.models.DomainStatus
import com.example.partnerkin.domain.models.TypeModel

fun ConferencesResponseDTO.toDomain(): List<ConferenceModel> {
    return this.data.result.map { it.toDomain() }
}

fun ConferenceDataDTO.toDomain(): ConferenceModel {
    return ConferenceModel(
        id = conference.id,
        name = conference.name,
        format = conference.format,
        status = conference.status.toDomain(),
        statusTitle = conference.statusTitle,
        url = conference.url,
        image = conference.image?.toDomain(),
        rating = conference.rating,
        startDate = conference.startDate,
        endDate = conference.endDate,
        oneday = conference.oneday,
        customDate = conference.customDate,
        countryId = conference.countryId,
        country = conference.country,
        cityId = conference.cityId,
        city = conference.city,
        categories = conference.categories.map { it.toDomain() },
        typeId = conference.typeId,
        type = conference.type.toDomain()
    )
}

fun Status.toDomain(): DomainStatus {
    return when(this) {
        Status.CANCELED -> DomainStatus.CANCELED
        Status.PUBLISH -> DomainStatus.PUBLISH
    }
}

fun ImageDTO.toDomain(): ImageModel {
    return ImageModel(
        id = id,
        url = url,
        preview = preview,
        placeholderColor = placeholderColor
    )
}

fun CategoryDTO.toDomain(): CategoryModel {
    return CategoryModel(
        id = id,
        name = name,
        url = url
    )
}

fun TypeDTO.toDomain(): TypeModel {
    return TypeModel(
        id = id,
        name = name
    )
}

fun ConferenceDetailsDTO.toDomain(): ConferenceDetailsModel {
    return ConferenceDetailsModel(
        id = id,
        name = name,
        format = format,
        status = status.toDomain(),
        statusTitle = statusTitle,
        url = url,
        image = image?.toDomain(),
        rating = rating,
        startDate = startDate,
        endDate = endDate,
        oneday = oneday,
        customDate = customDate,
        countryId = countryId,
        country = country,
        cityId = cityId,
        city = city,
        categories = categories.map { it.toDomain() },
        typeId = typeId,
        type = type.toDomain(),
        registerUrl = registerUrl,
        about = about
    )
}
