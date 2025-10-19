package com.example.partnerkin.data.remote

import com.example.partnerkin.data.models.ConferenceDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(private val client: HttpClient) {

    suspend fun getConferences(): List<ConferenceDTO> {
        val url = "https://partnerkin.com/api_ios_test/list?api_key=DMwdj29q@S29shslok2"
        return client.get(url).body()
    }
}
