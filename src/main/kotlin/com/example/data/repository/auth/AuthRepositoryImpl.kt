package com.example.data.repository.auth

import com.example.data.response.BaseResponse
import com.example.data.response.OTPResponse
import com.example.data.service.auth.AuthService
import com.example.plugins.route.auth.CreateUserParams
import com.example.plugins.route.auth.UserLoginParams
import com.example.plugins.route.auth.UserParams
import com.example.plugins.security.token.JwtConfig
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
                BaseResponse.ErrorResponse(message = GENERIC_ERROR)
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
            BaseResponse.ErrorResponse(message = USER_LOGIN_FAILURE)
        }
    }

    override suspend fun resetUserCred(params: UserParams): BaseResponse<Any> {

        return if (!isEmailExist(params.email)) {
            BaseResponse.ErrorResponse(message = MESSAGE_EMAIL_NOT_REGISTERED)
        } else {
            val user = authService.updateUserCred(params)
            if (user != null) {
                BaseResponse.SuccessResponse(data = "", message = USER_PASSWORD_UPDATED_SUCCESS)
            } else {
                BaseResponse.ErrorResponse(message = GENERIC_ERROR)
            }
        }
    }

    override suspend fun verifyEmail(params: UserParams): BaseResponse<Any> =

        when (EMAIL_ADDRESS_PATTERN.matcher(params.email).matches()) {
            true -> sendOTPEmail(params)
            else -> BaseResponse.SuccessResponse(data = "", message = USER_INVALID_FAILURE)
        }

    override suspend fun sendOTPEmail(params: UserParams): BaseResponse<Any> = if (!isEmailExist(params.email)) {
        BaseResponse.ErrorResponse(message = USER_INVALID_FAILURE)
    } else {

        val otp = getRandomNumber()
        sendOtpEmail(otp, params.email, params.fullName ?: TECH_NEST)

        val response = OTPResponse(otp = otp)
        BaseResponse.SuccessResponse(data = response, message = USER_VERIFY_EMAIL)
    }

    private suspend fun isEmailExist(email: String): Boolean = authService.findUserByEmail(email) != null
}