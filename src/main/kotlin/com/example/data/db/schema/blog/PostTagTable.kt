package com.example.data.db.schema.blog

import org.jetbrains.exposed.sql.Table

object PostTagTable : Table("blog_post_tags") {
    val id = integer("id").autoIncrement()
    val postId = integer("post_id").references(PostTable.id)
    val tagId = integer("tag_id").references(TagTable.id)
    override val primaryKey = PrimaryKey(id)
}