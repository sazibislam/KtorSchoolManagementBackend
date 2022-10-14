package com.example.data.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Comment(
    val id: Int,
    val comment: String,
    val createdAt: String
)