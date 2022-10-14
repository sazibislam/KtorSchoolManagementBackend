package com.example.data.repository.user

import com.example.data.response.BaseResponse
import com.example.data.service.user.UserService
import com.example.utils.GENERIC_ERROR
import com.example.utils.USER_ID_FIELD_FAILURE
import com.example.utils.USER_LOGOUT_SUCCESS

class UserRepositoryImpl(private val userService: UserService) : UserRepository {

    override suspend fun getUser(id: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = userService.getUser(id))
    }

    override suspend fun logoutUser(id: Int): BaseResponse<Any> {
        //invalidate token
        return BaseResponse.SuccessResponse(data = "", message = USER_LOGOUT_SUCCESS)
    }

    override suspend fun getNotification(id: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = userService.getAllNotification(id))
    }

    override suspend fun deleteNotification(id: Int): BaseResponse<Any> {
        return if (userService.deleteAllNotification(id)) BaseResponse.SuccessResponse(data = "")
        else BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun getPosts(id: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = userService.getPosts(id))
    }

    override suspend fun getPostDetails(postId: Int?): BaseResponse<Any> =
        postId?.let {
            return BaseResponse.SuccessResponse(data = userService.getPostDetails(postId))
        } ?: BaseResponse.ErrorResponse(message = USER_ID_FIELD_FAILURE)

    override suspend fun deletePostComment(commentId: Int?): BaseResponse<Any> =
        commentId?.let {
            return BaseResponse.SuccessResponse(data = userService.deletePostComment(commentId))
        } ?: BaseResponse.ErrorResponse(message = USER_ID_FIELD_FAILURE)

}