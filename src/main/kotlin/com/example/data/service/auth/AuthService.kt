package com.example.data.service.auth

import com.example.data.model.User
import com.example.plugins.route.auth.CreateUserParams

interface AuthService {
    suspend fun registerUser(params: CreateUserParams): User?
    suspend fun loginUser(email: String, password: String): User?
    suspend fun findUserByEmail(email: String): User?
    suspend fun forgetUserPassword(email: String, password: String): User?
}