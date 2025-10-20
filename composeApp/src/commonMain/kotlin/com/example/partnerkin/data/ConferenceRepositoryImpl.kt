package com.example.partnerkin.data

import com.example.partnerkin.data.models.utils.toDomain
import com.example.partnerkin.data.remote.ApiService
import com.example.partnerkin.domain.models.ConferenceModel
import com.example.partnerkin.domain.ConferenceRepository
import com.example.partnerkin.domain.models.ConferenceDetailsModel

class ConferenceRepositoryImpl(
    private val apiService: ApiService
): ConferenceRepository {
    override suspend fun getConferences(): Result<List<ConferenceModel>> {
        return runCatching {
            apiService.getConferences().toDomain()
        }
    }

    override suspend fun getConferenceDetails(): Result<ConferenceDetailsModel> {
        return runCatching {
            apiService.getConferenceDetails().data.toDomain()
        }
    }
}