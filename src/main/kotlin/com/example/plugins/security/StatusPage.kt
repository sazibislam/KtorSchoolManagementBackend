package com.example.plugins.security

import com.example.data.response.BaseResponse
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*


fun Application.configureStatusPages() {
    install(StatusPages) {

        exception<Throwable> { call, cause_ ->

            val error = when (cause_) {
                is MissingKotlinParameterException -> BaseResponse.ErrorResponse("Missing attribute ${cause_.parameter.name}")
                is JsonParseException -> BaseResponse.ErrorResponse(cause_.message!!)
                else -> BaseResponse.ErrorResponse(cause_.message!!)
            }
            call.respond(error.statusCode, error)
        }
    }
}