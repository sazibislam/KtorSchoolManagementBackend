package com.example.data.service.user

import com.example.data.model.User


interface UserService {
    suspend fun getUser(id: Int): User
}