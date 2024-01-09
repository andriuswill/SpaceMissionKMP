package com.andriuswill.spacemissionkmp.data.remote.repository

import com.andriuswill.spacemissionkmp.data.LaunchesApi
import com.andriuswill.spacemissionkmp.data.remote.model.LaunchDto
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments

class LaunchesRepositoryImpl(
    private val api : LaunchesApi
) : LaunchesRepository {

    companion object {
        const val URL_PATH = "launches"
    }

    override suspend fun fetchLaunches(): List<LaunchDto> {
        return api.httpClient.get(LaunchesApi.URL) {
            this.url.appendPathSegments(URL_PATH)
        }.body()
    }
}