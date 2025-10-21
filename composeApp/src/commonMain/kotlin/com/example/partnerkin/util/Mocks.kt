package com.example.partnerkin.util

import com.example.partnerkin.domain.models.CategoryModel
import com.example.partnerkin.domain.models.ConferenceDetailsModel
import com.example.partnerkin.domain.models.ConferenceModel
import com.example.partnerkin.domain.models.DomainStatus
import com.example.partnerkin.domain.models.ImageModel
import com.example.partnerkin.domain.models.TypeModel
import kotlinx.datetime.LocalDate

object Mocks {
    val normalConference = ConferenceModel(
        id = 1,
        name = "KMP Forward 2025 fewf ewfw qfewf wdbjqq wdqdjbqw qdjbqfq fwfwe",
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
            CategoryModel(2, "Kotlin", "kotlin"),
            CategoryModel(3, "Kotlin", "kotlin"),
            CategoryModel(4, "Kotlin", "kotlin"),
            CategoryModel(5, "Kotlin", "kotlin"),
            CategoryModel(6, "Kotlin fefwfew wfefwf wf wfewf", "kotlin"),
        ),
        typeId = 1,
        type = TypeModel(1, "Conference")
    )

    val canceledConference = ConferenceModel(
        id = 2,
        name = "Global Marketing Summit 2025 fewf ewfw qfewf wdbjqq wdqdjbqw qdjbqfq fwfwe\"",
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
        country = "UK fwefwe fewfw fw fewf ewf fwf wef we fewf wef wfwef",
        cityId = 2,
        city = "London",
        categories = listOf(
            CategoryModel(3, "Marketing", "marketing"),
            CategoryModel(4, "Business", "business"),
            CategoryModel(5, "Business wefwf fwefw", "business qwdjvhqdj  dwqjdhqwj d dqwdjhwqjdhd qwjdhwqjdhqwjqdwqjd"),
            CategoryModel(6, "Business", "business"),
            CategoryModel(7, "Business", "business"),
        ),
        typeId = 2,
        type = TypeModel(2, "Summit")
    )

    val oneDayConference = ConferenceModel(
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

    val conferencesExample = listOf(
        normalConference,
        canceledConference,
        oneDayConference
    )

    val sigmaAfrica2025 = ConferenceDetailsModel(
        id = 3228,
        name = "SiGMA Africa 2025",
        format = "offline",
        status = DomainStatus.PUBLISH,
        statusTitle = "Опубликована",
        url = "sigma-africa-2025",
        image = ImageModel(
            id = "f4bad773d6e5c03bc934706d88785bbf",
            url = "https://partnerkin.com/uploads/webp/conferences/81e62a7f948c48175e7d5eaa.webp",
            preview = "https://partnerkin.com/uploads/comp/webp/conferences/81e62a7f948c48175e7d5eaa.webp",
            placeholderColor = null,
        ),
        rating = null,
        startDate = LocalDate.parse("2025-03-10"),
        endDate = LocalDate.parse("2025-03-12"),
        oneday = 0,
        customDate = null,
        countryId = 52,
        country = "ЮАР",
        cityId = 1274,
        city = "Кейптаун",
        categories = listOf(
            CategoryModel(id = 4, name = "Affiliate", url = "affiliate"),
            CategoryModel(id = 5, name = "Marketing", url = "marketing"),
            CategoryModel(id = 9, name = "iGaming", url = "igaming"),
            CategoryModel(id = 10, name = "Betting", url = "betting")
        ),
        typeId = 2,
        type = TypeModel(
            id = 2,
            name = "Конференция"
        ),
        registerUrl = "https://partnerkin.com/events/sigma-africa-2025",
        about = "SiGMA Africa 2025 пройдет с 10 по 12 марта в Кейптауне на территории Sun Exhibits. Организаторы планируют собрать 2500 представителей гемблинг и беттинг ниш, включая аффилиатов, операторов и поставщиков услуг."
    )


}