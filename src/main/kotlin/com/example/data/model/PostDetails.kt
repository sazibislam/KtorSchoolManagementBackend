package com.example.data.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PostDetails(
    val id: Int,
    val postId: Int,
    val description: String,
    var comments: List<Comment>? = null
)