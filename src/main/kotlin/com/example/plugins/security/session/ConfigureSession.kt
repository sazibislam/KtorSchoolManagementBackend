package com.example.plugins.security.session

import io.ktor.server.application.*
import io.ktor.server.sessions.*
import io.ktor.util.*

fun Application.configureSession() {

    install(Sessions) {
        cookie<UserSession>("user_session") {
            val secretEncryptKey = hex("00112233445566778899aabbccddeeff")
            val secretAuthKey = hex("02030405060708090a0b0c")
            cookie.extensions["SameSite"] = "lax"
            cookie.httpOnly = true
            transform(SessionTransportTransformerEncrypt(secretEncryptKey, secretAuthKey))
        }
    }
}