package com.example.partnerkin.domain

import com.example.partnerkin.domain.models.ConferenceModel

class GetConferencesUseCase(
    private val repository: ConferenceRepository
) {
    suspend fun invoke(): Result<List<ConferenceModel>> {
        return repository.getConferences()
    }
}