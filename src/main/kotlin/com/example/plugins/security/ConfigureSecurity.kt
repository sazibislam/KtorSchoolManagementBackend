package com.example.plugins.security

import com.example.data.response.BaseResponse
import com.example.utils.INVALID_AUTHENTICATION_TOKEN
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*


fun Application.configureSecurity(){
    JwtConfig.initialize("my-story-app")
    install(Authentication){
        jwt {
            verifier(JwtConfig.instance.verifier)
            validate {
                val claim = it.payload.getClaim(JwtConfig.CLAIM).asInt()
                if(claim != null){
                    UserIdPrincipalForUser(claim)
                }else {
                    null
                }
            }
            challenge { defaultScheme, realm ->
                call.respond(BaseResponse.ErrorResponse(INVALID_AUTHENTICATION_TOKEN))
            }
        }
    }
}