package com.example.partnerkin.domain

import com.example.partnerkin.domain.models.ConferenceDetailsModel

class GetConferenceDetailsUseCase(
    private val repository: ConferenceRepository
) {
    suspend fun invoke(): Result<ConferenceDetailsModel> {
        return repository.getConferenceDetails()
    }
}