package com.example.data.db.extention

import com.example.data.db.schema.NotificationTable
import com.example.data.db.schema.UserTable
import com.example.data.model.Notification
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

fun ResultRow?.toNotification(): Notification? {
    return if (this == null) null
    else Notification(
        id = this[NotificationTable.id],
        title = this[NotificationTable.title],
        note = this[NotificationTable.note],
        createdAt = this[NotificationTable.createdAt].toString(),
    )
}