package com.andriuswill.spacemissionkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform