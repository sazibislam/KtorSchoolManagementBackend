package com.example.plugins.route.user

import com.example.data.repository.user.UserRepository
import com.example.plugins.route.user.request.PostCommentParams
import com.example.plugins.security.UserPrincipalForUser
import com.example.utils.PACKAGE_ANDROID
import com.example.utils.PACKAGE_NAME
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.userRoutes(repository: UserRepository) {
    routing {
        header(PACKAGE_NAME, PACKAGE_ANDROID) {

            accept(ContentType("application", "json")) {
                authenticate {
                    route("/logout") {
                        post {
                            val principal = call.principal<UserPrincipalForUser>()
                            val result = repository.logoutUser(principal?.id!!)
                            call.respond(result.statusCode, result)
                        }
                    }

                    route("/user") {
                        get {
                            val principal = call.principal<UserPrincipalForUser>()
                            val result = repository.getUser(principal?.id!!)
                            call.respond(result.statusCode, result)
                        }
                    }

                    route("/notification") {
                        get {
                            val principal = call.principal<UserPrincipalForUser>()
                            val result = repository.getNotification(principal?.id!!)
                            call.respond(result.statusCode, result)
                        }
                    }

                    route("/delete_notification") {
                        post {
                            val principal = call.principal<UserPrincipalForUser>()
                            val result = repository.deleteNotification(principal?.id!!)
                            call.respond(result.statusCode, result)
                        }
                    }

                    route("/get_post") {
                        get {
                            val principal = call.principal<UserPrincipalForUser>()
                            val result = repository.getPosts(principal?.id!!)
                            call.respond(result.statusCode, result)
                        }
                    }

                    route("/get_post_details") {
                        get {
                            val postId = call.request.queryParameters["post_id"]?.toIntOrNull()
                            val result = repository.getPostDetails(postId)
                            postId?.let { id_ -> repository.incrementPostCounter(id = id_) }
                            call.respond(result.statusCode, result)
                        }
                    }

                    route("/add_post") {
                        post {
                            val principal = call.principal<UserPrincipalForUser>()
                            val multiPartData = call.receiveMultipart()
                            val result = repository.addPost(principal?.id!!, multiPartData)
                            call.respond(result.statusCode, result)
                        }
                    }

                    route("/delete_post") {
                        post {

                        }
                    }

                    route("/post_comment") {
                        post {
                            val params = call.receive<PostCommentParams>()
                            val result = repository.postComment(params)
                            call.respond(result.statusCode, result)
                        }
                    }

                    route("/delete_comment") {
                        post {
                            val commentId = call.request.queryParameters["comment_id"]?.toIntOrNull()
                            val result = repository.deletePostComment(commentId)
                            call.respond(result.statusCode, result)
                        }
                    }
                }
            }
        }
    }
}