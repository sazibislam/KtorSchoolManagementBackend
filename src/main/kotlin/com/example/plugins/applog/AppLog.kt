package com.example.plugins.applog

import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*

fun Application.configureAppLog() {
    install(CallLogging)
}

