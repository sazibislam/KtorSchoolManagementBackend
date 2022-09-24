package com.example.data.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Notification(
    val id: Int,
    val title: String,
    val note: String,
    val createdAt: String
)