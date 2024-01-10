package com.andriuswill.spacemissionkmp.domain.entities

data class Launch (
    val flightNumber: Int,
    val missionName: String,
    val launchDateUTC: String,
    val launchSuccess: Boolean?
)