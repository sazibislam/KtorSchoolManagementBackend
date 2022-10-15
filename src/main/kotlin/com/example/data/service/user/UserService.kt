package com.example.data.service.user

import com.example.data.model.Notification
import com.example.data.model.Post
import com.example.data.model.PostDetails
import com.example.data.model.User
import com.example.plugins.route.user.request.PostCommentParams


interface UserService {
    suspend fun getUser(id: Int): User
    suspend fun getAllNotification(id: Int): List<Notification>
    suspend fun deleteAllNotification(id: Int): Boolean
    suspend fun addPost(userId: Int, fileName: String, description: String, title: String, tag: String?): Boolean
    suspend fun getPosts(id: Int): List<Post>
    suspend fun getPostDetails(id: Int): PostDetails
    suspend fun postComment(params: PostCommentParams): Boolean
    suspend fun deletePostComment(commentId: Int): Boolean
    suspend fun incrementPostCounter(id: Int)
    suspend fun insertMocData(id: Int)

}