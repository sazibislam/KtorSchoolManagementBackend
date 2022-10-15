package com.example.data.service.user

import com.example.data.db.DatabaseFactory.dbQuery
import com.example.data.db.extention.*
import com.example.data.db.schema.NotificationTable
import com.example.data.db.schema.UserTable
import com.example.data.db.schema.blog.*
import com.example.data.model.Notification
import com.example.data.model.Post
import com.example.data.model.PostDetails
import com.example.data.model.User
import com.example.plugins.route.user.request.PostCommentParams
import com.example.utils.SUCCESS_MSG
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.update

class UserServiceImpl : UserService {
    override suspend fun getUser(id: Int): User {
        val userRow = dbQuery { UserTable.select { UserTable.id eq id }.first() }
        return userRow.toUser()!!
    }

    override suspend fun getAllNotification(id: Int): List<Notification> = dbQuery {
        NotificationTable.select { NotificationTable.userId eq id }.mapNotNull { row_ ->
            row_.toNotification()
        }
    }

    override suspend fun deleteAllNotification(id: Int): Boolean = dbQuery {
        NotificationTable.deleteWhere { NotificationTable.userId eq id } > 0
    }

    override suspend fun addPost(
        userId: Int,
        fileName: String,
        description: String,
        title: String,
        tag: String?
    ): String {

        val blogPostId = dbQuery {
            PostTable.insert {
                it[PostTable.userId] = userId
                it[image] = fileName
                it[published] = true
                it[PostTable.title] = title
                it[slug] = ""
            } get PostTable.id
        }

        dbQuery {
            PostDescriptionTable.insert {
                it[postId] = blogPostId
                it[PostDescriptionTable.description] = description
            }
        }

        tag?.let { tag_ ->

            var tagId: Int? = null
            dbQuery {
                tagId = TagTable.select { TagTable.name eq tag_ }.map { reach_ ->
                    reach_[TagTable.id]
                }.firstOrNull()
            }

            when (tagId) {
                null -> {
                    dbQuery {
                        val tagTableId = TagTable.insert { it[name] = tag } get TagTable.id
                        PostTagTable.insert {
                            it[postId] = blogPostId
                            it[PostTagTable.tagId] = tagTableId
                        }
                    }
                }
                else -> {
                    dbQuery {
                        PostTagTable.insert {
                            it[postId] = blogPostId
                            it[PostTagTable.tagId] = tagId!!
                        }
                    }
                }
            }
        }

        print("addPost, $fileName $description $title $tag")
        return SUCCESS_MSG
    }

    override suspend fun getPosts(id: Int): List<Post> = dbQuery {
        PostTable.select { PostTable.userId eq id }.mapNotNull { row_ ->
            val data = row_.toPost()
            if (data != null) {
                data.reach = PostReachTable.select { PostReachTable.postId eq data.id }.map { reach_ ->
                    reach_[PostReachTable.reach].toString()
                }.firstOrNull()
            }
            data
        }
    }

    override suspend fun getPostDetails(id: Int): PostDetails =
        dbQuery {
            PostDescriptionTable.select { PostDescriptionTable.postId eq id }.firstNotNullOf { row_ ->
                val data = row_.toPostDetails()
                if (data != null) {
                    data.comments = PostCommentTable.select { PostCommentTable.postId eq id }.mapNotNull { comment_ ->
                        comment_.toComment()
                    }
                }
                data
            }
        }

    override suspend fun postComment(params: PostCommentParams): Boolean {
        var statement = 0

        dbQuery {
            statement = PostCommentTable.insert {
                it[postId] = params.postId.toInt()
                it[comment] = params.comment
            } get PostCommentTable.id
        }
        return statement > 0
    }

    override suspend fun deletePostComment(commentId: Int): Boolean =
        dbQuery { PostCommentTable.deleteWhere { PostCommentTable.id eq commentId } > 0 }

    override suspend fun incrementPostCounter(id: Int) {

        val counter: Int? = dbQuery {
            PostReachTable.select { PostReachTable.postId eq id }.map { reach_ ->
                reach_[PostReachTable.reach]
            }.firstOrNull()
        }

        when (counter) {
            null -> dbQuery {
                PostReachTable.insert {
                    it[postId] = id
                    it[reach] = 1
                }
            }

            else -> dbQuery {
                PostReachTable.update {
                    it[postId] = id
                    it[reach] = counter + 1
                }
            }
        }
    }

    override suspend fun insertMocData(id: Int) {

        var statement: InsertStatement<Number>? = null
        dbQuery {
            statement = PostTable.insert {
                it[userId] = id
                it[title] = "Test Title $id"
                it[published] = true
                it[image] = ""
                it[slug] = ""
            }
        }

        dbQuery {
            TagTable.insert {
                it[name] = "Test Tag post id"
            }

            PostTagTable.insert {
                it[postId] = 15
                it[tagId] = 1
            }

            PostDescriptionTable.insert {
                it[postId] = 15
                it[description] = "Test Description"
            }
            PostCommentTable.insert {
                it[postId] = 15
                it[comment] = "Test Comment"
            }
            PostReachTable.insert {
                it[postId] = 15
                it[reach] = 1
            }
        }

    }
}