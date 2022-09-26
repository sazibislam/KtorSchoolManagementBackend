package com.example.plugins.security

import io.ktor.server.auth.*

data class UserPrincipalForUser(val id: Int) : Principal