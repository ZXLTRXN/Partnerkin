package com.example.partnerkin.util

import com.example.partnerkin.domain.models.CategoryModel
import com.example.partnerkin.domain.models.ConferenceModel
import com.example.partnerkin.domain.models.DomainStatus
import com.example.partnerkin.domain.models.ImageModel
import com.example.partnerkin.domain.models.TypeModel
import kotlinx.datetime.LocalDate

object Mocks {
    val conferences = listOf(
        ConferenceModel(
            id = 1,
            name = "KMP Forward 2025",
            format = "offline",
            status = DomainStatus.PUBLISH,
            statusTitle = "Published",
            url = "kmp-forward-2025",
            image = ImageModel(
                id = "1",
                url = "https://example.com/image1.png",
                preview = "https://example.com/preview1.png",
                placeholderColor = null
            ),
            rating = 4.8,
            startDate = LocalDate(2025, 10, 20),
            endDate = LocalDate(2025, 10, 22),
            oneday = 0,
            customDate = null,
            countryId = 1,
            country = "USA",
            cityId = 1,
            city = "San Francisco",
            categories = listOf(
                CategoryModel(1, "Mobile", "mobile"),
                CategoryModel(2, "Kotlin", "kotlin")
            ),
            typeId = 1,
            type = TypeModel(1, "Conference")
        ),
        ConferenceModel(
            id = 2,
            name = "Global Marketing Summit 2025",
            format = "online",
            status = DomainStatus.CANCELED,
            statusTitle = "Published",
            url = "global-marketing-summit-2025",
            image = ImageModel(
                id = "2",
                url = "https://example.com/image2.png",
                preview = "https://example.com/preview2.png",
                placeholderColor = null
            ),
            rating = 4.5,
            startDate = LocalDate(2025, 11, 5),
            endDate = LocalDate(2025, 11, 6),
            oneday = 0,
            customDate = null,
            countryId = 2,
            country = "UK",
            cityId = 2,
            city = "London",
            categories = listOf(
                CategoryModel(3, "Marketing", "marketing"),
                CategoryModel(4, "Business", "business")
            ),
            typeId = 2,
            type = TypeModel(2, "Summit")
        ),
        ConferenceModel(
            id = 3,
            name = "Design Systems Meetup",
            format = "offline",
            status = DomainStatus.PUBLISH,
            statusTitle = "Published",
            url = "design-systems-meetup",
            image = null,
            rating = null,
            startDate = LocalDate(2025, 10, 15),
            endDate = LocalDate(2025, 10, 15),
            oneday = 1,
            customDate = "Q4 2025",
            countryId = 3,
            country = "Germany",
            cityId = 3,
            city = "Berlin",
            categories = listOf(
                CategoryModel(5, "Design", "design"),
                CategoryModel(6, "UI/UX", "ui-ux")
            ),
            typeId = 3,
            type = TypeModel(3, "Meetup")
        )
    )
}