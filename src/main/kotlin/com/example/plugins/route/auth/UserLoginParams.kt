package com.example.plugins.route.auth

data class UserLoginParams(
    val email: String,
    val password: String,
    val uuid: String,
    val version : String
)