package com.example.data.repository.auth

import com.example.data.response.BaseResponse
import com.example.plugins.route.auth.CreateUserParams
import com.example.plugins.route.auth.UserParams
import com.example.plugins.route.auth.UserLoginParams

interface AuthRepository {
    suspend fun registerUser(params: CreateUserParams): BaseResponse<Any>
    suspend fun loginUser(params: UserLoginParams): BaseResponse<Any>
    suspend fun resetUserCred(params: UserParams): BaseResponse<Any>
    suspend fun verifyEmail(params: UserParams): BaseResponse<Any>
    suspend fun sendOTPEmail(params: UserParams): BaseResponse<Any>
}