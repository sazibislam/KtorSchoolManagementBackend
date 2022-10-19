package com.example.data.repository.auth

import com.example.data.model.User
import com.example.data.response.BaseResponse
import com.example.plugins.route.auth.CreateUserParams
import com.example.plugins.route.auth.UserLoginParams
import com.example.plugins.route.auth.UserParams

interface AuthRepository {
    suspend fun registerUser(params: CreateUserParams): BaseResponse<Any>
    suspend fun loginUser(params: UserLoginParams): User?
    suspend fun resetUserCred(params: UserParams): BaseResponse<Any>
    suspend fun verifyEmail(params: UserParams): BaseResponse<Any>
    suspend fun sendOTPEmail(params: UserParams): BaseResponse<Any>
}