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
        const val basePath = "launches"
        const val nextFilter = "next"
        const val lastFilter = "latest"
    }

    override suspend fun getLaunches(): List<LaunchDto> {
        return api.httpClient.get {
            this.url.appendPathSegments(basePath)
        }.body()
    }

    override suspend fun getNextLaunch(): LaunchDto {
        return api.httpClient.get {
            this.url.appendPathSegments("$basePath/$nextFilter")
        }.body()
    }

    override suspend fun getLastLaunch(): LaunchDto {
        return api.httpClient.get {
            this.url.appendPathSegments("$basePath/$lastFilter")
        }.body()
    }
}