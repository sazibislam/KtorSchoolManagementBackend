package com.example.plugins.route.auth

import com.example.data.repository.auth.AuthRepository
import com.example.data.response.BaseResponse
import com.example.plugins.security.UserPrincipalForUser
import com.example.plugins.security.token.JwtConfig
import com.example.utils.PACKAGE_ANDROID
import com.example.utils.PACKAGE_NAME
import com.example.utils.USER_LOGIN_FAILURE
import com.example.utils.USER_LOGIN_SUCCESS
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.authRoutes(repository: AuthRepository) {
    routing {
        header(PACKAGE_NAME, PACKAGE_ANDROID) {
            accept(ContentType("application", "json")) {
                route("/v1") {
                    post("/register") {
                        val params = call.receive<CreateUserParams>()
                        val result = repository.registerUser(params)
                        call.respond(result.statusCode, result)
                    }

                    post("/login") {
                        val params = call.receive<UserLoginParams>()
                        val user = repository.loginUser(params)

                        val result = if (user != null) {
                            val token = JwtConfig.instance.createAccessToken(id = user.id, uuid = params.uuid)
                            user.authToken = token

                            BaseResponse.SuccessResponse(data = user, message = USER_LOGIN_SUCCESS)
                        } else BaseResponse.ErrorResponse(message = USER_LOGIN_FAILURE)

                        call.respond(result.statusCode, result)
                    }

                    post("/forget") {
                        val params = call.receive<UserParams>()
                        val result = repository.sendOTPEmail(params)
                        call.respond(result.statusCode, result)
                    }

                    post("/reset") {
                        val params = call.receive<UserParams>()
                        val result = repository.resetUserCred(params)
                        call.respond(result.statusCode, result)
                    }

                    post("/verify") {
                        val params = call.receive<UserParams>()
                        val result = repository.verifyEmail(params)
                        call.respond(result.statusCode, result)
                    }
                }
            }
        }
    }
}