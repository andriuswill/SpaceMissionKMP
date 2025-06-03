package com.andriuswill.spacemissionkmp.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LaunchDetailsDto (
    @SerialName("flight_number")
    val flightNumber: Int,
    val name: String,
    @SerialName("date_utc")
    val launchDateUTC: String,
    val success: Boolean?,
    val links: LaunchLinks?,
)

@Serializable
class LaunchLinks(
    val patch: PatchLinks?,
    val reddit: RedditLinks?,
    val flickr: FlickerLinks?,
    val presskit: String?,
    val webcast: String?,
    @SerialName("youtube_id")
    val youtubeId: String?,
    val article: String?,
    val wikipedia: String?,
)

@Serializable
class PatchLinks(
    val small: String,
    val large: String,
)

@Serializable
class RedditLinks(
    val campaign: String?,
    val launch: String?,
    val media: String?,
    val recovery: String?,
)

@Serializable
class FlickerLinks(
    val small: List<String>,
    val original: List<String>,
)
