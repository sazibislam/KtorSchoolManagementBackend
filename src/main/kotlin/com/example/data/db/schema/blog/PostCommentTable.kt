package com.example.data.db.schema.blog

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object PostCommentTable : Table("blog_post_comment") {
    val id = integer("id").autoIncrement()
    val postId = integer("post_id").references(PostTable.id)
    val comment = text("comment")
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }

    override val primaryKey = PrimaryKey(id)
}