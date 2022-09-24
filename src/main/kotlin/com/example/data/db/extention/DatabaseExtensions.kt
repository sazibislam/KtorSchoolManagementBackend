package com.example.data.db.extention

import com.example.data.db.schema.UserTable
import com.example.data.model.User
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow?.toUser(): User? {
    return if (this == null) null
    else User(
        id = this[UserTable.id],
        fullName = this[UserTable.fullName],
        avatar = this[UserTable.avatar],
        email = this[UserTable.email],
        updatedAt = this[UserTable.updatedAt].toString(),
    )
}