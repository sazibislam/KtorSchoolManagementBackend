package com.example.data.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Post(
    val id: Int,
    val title: String,
    val image: String,
    val published: Boolean,
    var reach: String? = "",
    val createdAt: String,
    val updatedAt: String
)