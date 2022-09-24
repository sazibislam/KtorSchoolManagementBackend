package com.example.data.service.user

import com.example.data.db.DatabaseFactory.dbQuery
import com.example.data.db.extention.toNotification
import com.example.data.db.extention.toUser
import com.example.data.db.schema.NotificationTable
import com.example.data.db.schema.UserTable
import com.example.data.model.Notification
import com.example.data.model.User
import org.jetbrains.exposed.sql.select

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
}