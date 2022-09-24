package com.example.plugins.route.auth

data class CreateUserParams(
    val fullName: String,
    val email: String,
    val password: String,
    val avatar: String
)
