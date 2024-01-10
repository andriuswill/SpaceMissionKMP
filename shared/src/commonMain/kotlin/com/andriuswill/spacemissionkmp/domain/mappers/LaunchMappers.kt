package com.andriuswill.spacemissionkmp.domain.mappers

import com.andriuswill.spacemissionkmp.data.remote.model.LaunchDto
import com.andriuswill.spacemissionkmp.domain.entities.Launch

fun LaunchDto.toModel() = Launch(
    flightNumber = this.flightNumber,
    missionName = this.missionName,
    launchDateUTC = this.launchDateUTC,
    launchSuccess = this.launchSuccess
)