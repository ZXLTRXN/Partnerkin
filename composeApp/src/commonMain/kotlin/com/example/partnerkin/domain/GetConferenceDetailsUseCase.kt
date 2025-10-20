package com.example.partnerkin.domain

import com.example.partnerkin.domain.models.ConferenceModel

class GetConferenceDetailsUseCase(
    private val repository: ConferenceRepository
) {
    suspend fun invoke(): Result<List<ConferenceModel>> { // TODO
        return repository.getConferences()
    }
}