package com.example.data.repository.user

import com.example.data.response.BaseResponse


interface UserRepository {
    suspend fun getUser(id: Int): BaseResponse<Any>
    suspend fun logoutUser(id: Int): BaseResponse<Any>
    suspend fun getNotification(id: Int): BaseResponse<Any>
    suspend fun deleteNotification(id: Int): BaseResponse<Any>
    suspend fun getPosts(id: Int): BaseResponse<Any>
    suspend fun getPostDetails(postId: Int?): BaseResponse<Any>

}