package com.example.data.db.schema.blog

import org.jetbrains.exposed.sql.Table

object PostsDescriptionTable : Table("blog_post_description") {
    val id = integer("id").autoIncrement()
    val postId = integer("post_id").references(PostsTable.id)
    val description = text("description")
    override val primaryKey = PrimaryKey(id)
}