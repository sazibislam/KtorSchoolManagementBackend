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

    override suspend fun getPostDetails(id: Int): PostDetails {

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

        return dbQuery {
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
    }

    override suspend fun deletePostComment(id: Int): PostDetails {
        TODO("Not yet implemented")
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