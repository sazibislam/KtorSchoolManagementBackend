package com.example.data.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Post(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val published: Boolean,
    var comments: List<Comment>? = null,
    var reach: String? = "",
    val createdAt: String,
    val updatedAt: String
)