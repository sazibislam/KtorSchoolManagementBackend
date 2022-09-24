package com.example.plugins

import com.example.di.RepositoryProvider
import com.example.plugins.route.auth.authRoutes
import com.example.plugins.route.user.userRoutes
import io.ktor.server.application.*

fun Application.configureRouting() {
    authRoutes(RepositoryProvider.provideAuthRepository())
    userRoutes(RepositoryProvider.provideUserRepository())
}
