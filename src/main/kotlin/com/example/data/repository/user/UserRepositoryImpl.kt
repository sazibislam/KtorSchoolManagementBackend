package com.example.data.repository.user

import com.example.data.response.BaseResponse
import com.example.data.service.user.UserService
import com.example.plugins.route.user.request.PostCommentParams
import com.example.utils.GENERIC_ERROR
import com.example.utils.SUCCESS_MSG
import com.example.utils.USER_LOGOUT_SUCCESS
import io.ktor.http.content.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

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

    override suspend fun addPost(id: Int, partData: MultiPartData): BaseResponse<Any> {

        var description = ""
        var title = ""
        var tag = ""
        var fileName = ""

        withContext(Dispatchers.IO) {
            try {
                partData.forEachPart { part_ ->

                    when (part_) {
                        is PartData.FormItem -> {
                            when (part_.name) {
                                "title" -> title = part_.value
                                "description" -> description = part_.value
                                "tag" -> tag = part_.value
                            }
                        }
                        is PartData.FileItem -> {
                            fileName = part_.originalFileName!!
                            val file = File("/upload/post/$fileName")

                            part_.streamProvider().use { item ->
                                file.outputStream().buffered().use { item.copyTo(it) }
                            }
                        }
                        else -> {}
                    }
                    part_.dispose()
                }
            } catch (e: Exception) {
                print("sazib ${e.message}")
            }
        }
        return BaseResponse.SuccessResponse(data = userService.addPost(id, fileName, description, title, tag))
    }

    override suspend fun getPosts(id: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = userService.getPosts(id))
    }

    override suspend fun getPostDetails(postId: Int?): BaseResponse<Any> =
        postId?.let {
            return BaseResponse.SuccessResponse(data = userService.getPostDetails(postId))
        } ?: BaseResponse.ErrorResponse(message = GENERIC_ERROR)

    override suspend fun postComment(params: PostCommentParams): BaseResponse<Any> {
        return if (userService.postComment(params)) BaseResponse.SuccessResponse(data = "", message = SUCCESS_MSG)
        else BaseResponse.ErrorResponse(message = GENERIC_ERROR)
    }

    override suspend fun deletePostComment(commentId: Int?): BaseResponse<Any> =
        commentId?.let {
            return if (userService.deletePostComment(commentId)) BaseResponse.SuccessResponse(data = "")
            else BaseResponse.ErrorResponse(message = GENERIC_ERROR)
        } ?: BaseResponse.ErrorResponse(message = GENERIC_ERROR)

    override suspend fun incrementPostCounter(id: Int) = userService.incrementPostCounter(id)
    override suspend fun deletePost(postId: Int?): BaseResponse<Any> =
        postId?.let {
            return if (userService.deletePost(postId)) BaseResponse.SuccessResponse(data = "")
            else BaseResponse.ErrorResponse(message = GENERIC_ERROR)
        } ?: BaseResponse.ErrorResponse(message = GENERIC_ERROR)


}