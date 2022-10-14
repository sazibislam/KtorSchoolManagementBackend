package com.example.data.response

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.ktor.http.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize
sealed class BaseResponse<T>(
    @JsonIgnore open val statusCode: HttpStatusCode
) {
    @JsonSerialize
    data class SuccessResponse<T>(
        val message: String? = null,
        val status: String = "success",
        val data: T,
        @JsonIgnore
        override val statusCode: HttpStatusCode = HttpStatusCode.OK
    ) : BaseResponse<T>(statusCode)

    @JsonSerialize
    data class ErrorResponse(
        val message: String,
        val status: String = "error",
        @JsonIgnore
        override val statusCode: HttpStatusCode = HttpStatusCode.OK
    ) : BaseResponse<Any>(statusCode)
}