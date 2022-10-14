package com.example.data.db.extention

import com.example.data.db.schema.NotificationTable
import com.example.data.db.schema.UserTable
import com.example.data.db.schema.blog.PostCommentTable
import com.example.data.db.schema.blog.PostDescriptionTable
import com.example.data.db.schema.blog.PostReachTable
import com.example.data.db.schema.blog.PostTable
import com.example.data.model.Comment
import com.example.data.model.Notification
import com.example.data.model.Post
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

fun ResultRow?.toPost(): Post? {
    return if (this == null) null
    else Post(
        id = this[PostTable.id],
        title = this[PostTable.title],
        description = this[PostDescriptionTable.description],
        published = this[PostTable.published],
        image = this[PostTable.image],
        comments = null,
        reach = this[PostReachTable.reach].toString(),
        createdAt = this[PostTable.createdAt].toString(),
        updatedAt = this[PostTable.updatedAt].toString()
    )
}

fun ResultRow?.toComment(): Comment? {
    return if (this == null) null
    else Comment(
        id = this[PostCommentTable.id],
        comment = this[PostCommentTable.comment],
        createdAt = this[PostCommentTable.createdAt].toString()
    )
}