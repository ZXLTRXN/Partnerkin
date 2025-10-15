package com.example.partnerkin

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform