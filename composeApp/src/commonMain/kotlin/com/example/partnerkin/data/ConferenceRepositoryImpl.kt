package com.example.partnerkin.data

import com.example.partnerkin.data.models.mapper.toDomain
import com.example.partnerkin.data.remote.ApiService
import com.example.partnerkin.domain.models.ConferenceModel
import com.example.partnerkin.domain.ConferenceRepository


class ConferenceRepositoryImpl(
    private val apiService: ApiService
): ConferenceRepository {

    override suspend fun getConferences(): Result<List<ConferenceModel>> {
        return runCatching {
            apiService.getConferences().toDomain()
        }
    }
}