package com.example.partnerkin.domain

import com.example.partnerkin.domain.models.ConferenceModel

interface ConferenceRepository {
    suspend fun getConferences(): Result<List<ConferenceModel>>
}