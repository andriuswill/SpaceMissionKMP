package com.andriuswill.spacemissionkmp.data.remote.repository

import com.andriuswill.spacemissionkmp.data.LaunchesApi
import com.andriuswill.spacemissionkmp.data.remote.model.LaunchDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments

class LaunchesRepositoryImpl : LaunchesRepository {

    private val httpClient = LaunchesApi().httpClient

    companion object {
        const val URL_PATH = "launches"
    }

    override suspend fun fetchLaunches(): List<LaunchDto> {
        return httpClient.get(LaunchesApi.URL) {
            this.url.appendPathSegments(URL_PATH)
        }.body()
    }
}