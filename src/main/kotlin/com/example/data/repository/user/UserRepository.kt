package com.example.data.repository.user

import com.example.data.response.BaseResponse


interface UserRepository {
    suspend fun getUser(id: Int): BaseResponse<Any>
}