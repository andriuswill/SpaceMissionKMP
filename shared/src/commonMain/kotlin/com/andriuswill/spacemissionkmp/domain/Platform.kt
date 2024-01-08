package com.andriuswill.spacemissionkmp.domain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform