package com.example

import com.example.plugins.configureDatabase
import com.example.plugins.configureMonitoring
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import com.example.plugins.security.configureSecurity
import com.example.plugins.security.configureStatusPages
import io.ktor.server.application.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    configureDatabase()
    configureRouting()
    configureSerialization()
    configureMonitoring()
    configureStatusPages()
    configureSecurity()
//    configureAppLog()
}
