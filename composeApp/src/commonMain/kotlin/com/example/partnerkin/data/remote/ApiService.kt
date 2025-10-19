package com.example.partnerkin.data.remote

import com.example.partnerkin.data.models.ConferencesResponseDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(private val client: HttpClient) {

    suspend fun getConferences(): ConferencesResponseDTO {
        return client.get("list").body()
    }
}
