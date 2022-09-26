package com.example.plugins.security.token

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

class JwtConfig private constructor(secret: String) {

    private val validityInMs = 1000 * 3600 * 24 * 7 //7d
    private val algorithm = Algorithm.HMAC256(secret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .build()

    fun createAccessToken(id: Int): String = JWT
        .create()
        .withSubject("Android-uuid")
        .withIssuedAt(Date())
        .withExpiresAt(getExpiration())
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .withClaim(CLAIM, id)
        .sign(algorithm)

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)

    companion object {
        private const val ISSUER = "my-story-app" //"http://0.0.0.0:8080/"
        private const val AUDIENCE = "my-story-app" //"http://0.0.0.0:8080/hello"
        const val SECURITY = "my-story-app" //J0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9
        const val CLAIM = "id"

        lateinit var instance: JwtConfig
            private set

        fun initialize(secret: String) {
            synchronized(this) {
                if (!this::instance.isInitialized) {
                    instance = JwtConfig(secret)
                }
            }
        }
    }
}