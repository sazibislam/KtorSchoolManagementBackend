package com.example.plugins.security.session

import io.ktor.server.auth.*

data class UserSession(val userid: String, val count: Int = 0) : Principal