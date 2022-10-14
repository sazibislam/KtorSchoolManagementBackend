package com.example.data.db.schema.blog

import org.jetbrains.exposed.sql.Table

object PostDescriptionTable : Table("blog_post_description") {
    val id = integer("id").autoIncrement()
    val postId = integer("post_id").references(PostTable.id)
    val description = text("description")
    override val primaryKey = PrimaryKey(id)
}