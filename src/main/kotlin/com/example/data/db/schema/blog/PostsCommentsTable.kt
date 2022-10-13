package com.example.data.db.schema.blog

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object PostsCommentsTable : Table("blog_post_comments") {
    val id = integer("id").autoIncrement()
    val postId = integer("post_id").references(PostsTable.id)
    val comments = text("comments")
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }

    override val primaryKey = PrimaryKey(id)
}