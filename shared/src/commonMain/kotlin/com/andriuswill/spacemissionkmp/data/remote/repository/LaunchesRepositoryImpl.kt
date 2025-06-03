package com.andriuswill.spacemissionkmp.data.remote.repository

import com.andriuswill.spacemissionkmp.data.LaunchesApi
import com.andriuswill.spacemissionkmp.data.remote.model.LaunchDetailsDto
import com.andriuswill.spacemissionkmp.data.remote.model.LaunchDto
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments

class LaunchesRepositoryImpl(
    private val api : LaunchesApi
) : LaunchesRepository {

    companion object {
        private const val LAUNCHES = "launches"
        private const val PAST = "past"
        private const val UPCOMING = "upcoming"
        private const val NEXT = "next"
        private const val LAST = "latest"
    }

    override suspend fun getPastLaunches(): List<LaunchDto> {
        return api.httpClient.get {
            this.url.appendPathSegments("$LAUNCHES/$PAST")
        }.body()
    }

    override suspend fun getUpcomingLaunches(): List<LaunchDto> {
        return api.httpClient.get {
            this.url.appendPathSegments("$LAUNCHES/$UPCOMING")
        }.body()
    }

    override suspend fun getNextLaunch(): LaunchDto {
        return api.httpClient.get {
            this.url.appendPathSegments("$LAUNCHES/$NEXT")
        }.body()
    }

    override suspend fun getLastLaunch(): LaunchDto {
        return api.httpClient.get {
            this.url.appendPathSegments("$LAUNCHES/$LAST")
        }.body()
    }

    override suspend fun getLaunchDetail(launchId: String): LaunchDetailsDto {
        return api.httpClient.get {
            this.url.appendPathSegments("$LAUNCHES/$launchId")
        }.body()
    }
}