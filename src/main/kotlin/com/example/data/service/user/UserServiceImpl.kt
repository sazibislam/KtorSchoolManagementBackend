package com.example.data.service.user

import com.example.data.db.DatabaseFactory.dbQuery
import com.example.data.db.extention.toUser
import com.example.data.db.schema.UserTable
import com.example.data.model.User
import org.jetbrains.exposed.sql.select

class UserServiceImpl : UserService {
    override suspend fun getUser(id: Int): User {
        val userRow = dbQuery { UserTable.select { UserTable.id eq id }.first() }
        return userRow.toUser()!!
    }
}