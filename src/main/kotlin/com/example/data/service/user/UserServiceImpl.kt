package com.example.data.service.user

import com.example.data.db.DatabaseFactory.dbQuery
import com.example.data.db.extention.toComment
import com.example.data.db.extention.toNotification
import com.example.data.db.extention.toPost
import com.example.data.db.extention.toUser
import com.example.data.db.schema.NotificationTable
import com.example.data.db.schema.UserTable
import com.example.data.db.schema.blog.*
import com.example.data.model.Notification
import com.example.data.model.Post
import com.example.data.model.User
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

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
                data.comments = PostCommentTable.select { PostCommentTable.postId eq data.id }.mapNotNull { comment_ ->
                    comment_.toComment()
                }
                data.reach = PostReachTable.select { PostReachTable.postId eq data.id }.map { reach_ ->
                    reach_[PostReachTable.reach].toString()
                }.firstOrNull()
            }
            data
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