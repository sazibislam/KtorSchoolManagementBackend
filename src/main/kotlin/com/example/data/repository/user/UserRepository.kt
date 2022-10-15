package com.example.data.repository.user

import com.example.data.response.BaseResponse
import com.example.plugins.route.user.request.PostCommentParams
import io.ktor.http.content.*


interface UserRepository {
    suspend fun getUser(id: Int): BaseResponse<Any>
    suspend fun logoutUser(id: Int): BaseResponse<Any>
    suspend fun getNotification(id: Int): BaseResponse<Any>
    suspend fun deleteNotification(id: Int): BaseResponse<Any>
    suspend fun addPost(id: Int, partData: MultiPartData): BaseResponse<Any>
    suspend fun getPosts(id: Int): BaseResponse<Any>
    suspend fun getPostDetails(postId: Int?): BaseResponse<Any>
    suspend fun postComment(params: PostCommentParams): BaseResponse<Any>
    suspend fun deletePostComment(commentId: Int?): BaseResponse<Any>
    suspend fun incrementPostCounter(id: Int)

}