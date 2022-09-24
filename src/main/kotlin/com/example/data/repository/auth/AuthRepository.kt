package com.example.data.repository.auth

import com.example.data.response.BaseResponse
import com.example.plugins.route.auth.CreateUserParams
import com.example.plugins.route.auth.ForgetUserParams
import com.example.plugins.route.auth.UserLoginParams

interface AuthRepository {
    suspend fun registerUser(params: CreateUserParams): BaseResponse<Any>
    suspend fun loginUser(params: UserLoginParams): BaseResponse<Any>

    suspend fun sendForgotPasswordEmail(params: ForgetUserParams): BaseResponse<Any>
    suspend fun verifyEmail(email: String): BaseResponse<Any>
    suspend fun sendOTPEmail(email: String): BaseResponse<Any>
}