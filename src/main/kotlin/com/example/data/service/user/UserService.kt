package com.example.data.service.user

import com.example.data.model.Notification
import com.example.data.model.Post
import com.example.data.model.PostDetails
import com.example.data.model.User


interface UserService {
    suspend fun getUser(id: Int): User
    suspend fun getAllNotification(id: Int): List<Notification>
    suspend fun deleteAllNotification(id: Int): Boolean
    suspend fun getPosts(id: Int): List<Post>
    suspend fun getPostDetails(id: Int): PostDetails
    suspend fun deletePostComment(id: Int): PostDetails
    suspend fun insertMocData(id: Int)

}