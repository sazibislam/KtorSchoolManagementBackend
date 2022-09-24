package com.example.plugins.route.auth

data class UserParams(
    val fullName: String? = null,
    val email: String,
    val password: String? = null,
    val avatar: String? = null
)
