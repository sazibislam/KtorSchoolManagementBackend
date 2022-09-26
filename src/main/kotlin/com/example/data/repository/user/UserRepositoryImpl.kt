package com.example.data.repository.user

import com.example.data.response.BaseResponse
import com.example.data.service.user.UserService
import com.example.utils.USER_LOGOUT_SUCCESS

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {

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
}