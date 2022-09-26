package com.example.plugins.route.user

import com.example.data.repository.user.UserRepository
import com.example.plugins.security.UserIdPrincipalForUser
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.userRoutes(repository: UserRepository) {
    routing {
        authenticate {
            route("/logout") {
                post {
                    val principal = call.principal<UserIdPrincipalForUser>()
                    val result = repository.logoutUser(principal?.id!!)
                    call.respond(result.statusCode, result)
                }
            }

            route("/user") {
                get {
                    val principal = call.principal<UserIdPrincipalForUser>()
                    val result = repository.getUser(principal?.id!!)
                    call.respond(result.statusCode, result)
                }
            }

            route("/notification") {
                get {
                    val principal = call.principal<UserIdPrincipalForUser>()
                    val result = repository.getNotification(principal?.id!!)
                    call.respond(result.statusCode, result)
                }
            }
        }
    }
}