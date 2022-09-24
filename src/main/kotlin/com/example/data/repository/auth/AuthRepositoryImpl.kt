package com.example.data.repository.auth

import com.example.data.response.BaseResponse
import com.example.data.response.OTPResponse
import com.example.data.service.auth.AuthService
import com.example.plugins.route.auth.CreateUserParams
import com.example.plugins.route.auth.ForgetUserParams
import com.example.plugins.route.auth.UserLoginParams
import com.example.plugins.security.JwtConfig
import com.example.utils.*

class AuthRepositoryImpl(
    private val authService: AuthService
) : AuthRepository {

    override suspend fun registerUser(params: CreateUserParams): BaseResponse<Any> {
        return if (isEmailExist(params.email)) {
            BaseResponse.ErrorResponse(message = MESSAGE_EMAIL_ALREADY_REGISTERED)
        } else {
            val user = authService.registerUser(params)
            if (user != null) {
                val token = JwtConfig.instance.createAccessToken(user.id)
                user.authToken = token
                BaseResponse.SuccessResponse(data = user, message = USER_REGISTRATION_SUCCESS)
            } else {
                BaseResponse.ErrorResponse(GENERIC_ERROR)
            }
        }
    }

    override suspend fun loginUser(params: UserLoginParams): BaseResponse<Any> {
        val user = authService.loginUser(params.email, params.password)
        return if (user != null) {
            val token = JwtConfig.instance.createAccessToken(user.id)
            user.authToken = token
            BaseResponse.SuccessResponse(data = user, message = USER_LOGIN_SUCCESS)
        } else {
            BaseResponse.ErrorResponse(USER_LOGIN_FAILURE)
        }
    }

    override suspend fun sendForgotPasswordEmail(params: ForgetUserParams): BaseResponse<Any> =
        sendOTPEmail(params.email)

    override suspend fun verifyEmail(email: String): BaseResponse<Any> =
        if (!isEmailExist(email)) {
            BaseResponse.ErrorResponse(message = USER_INVALID_FAILURE)
        } else {
            val user = authService.findUserByEmail(email)
            BaseResponse.SuccessResponse(data = user!!, message = USER_PASSWORD_UPDATED_SUCCESS)
        }

    override suspend fun sendOTPEmail(email: String): BaseResponse<Any> =
        if (!isEmailExist(email)) {
            BaseResponse.ErrorResponse(message = USER_INVALID_FAILURE)
        } else {

            val otp = getRandomNumber()
            sendOtpEmail(otp, email, "")

            val response = OTPResponse(otp = otp)
            BaseResponse.SuccessResponse(data = response, message = USER_VERIFY_EMAIL)
        }

    private suspend fun isEmailExist(email: String): Boolean = authService.findUserByEmail(email) != null
}