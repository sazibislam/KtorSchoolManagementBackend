package com.example.data.db.schema.blog

import org.jetbrains.exposed.sql.Table

object PostsTagTable : Table("blog_post_tags") {
    val id = integer("id").autoIncrement()
    val postId = integer("post_id").references(PostsTable.id)
    val tagId = integer("tag_id").references(TagsTable.id)
    override val primaryKey = PrimaryKey(id)
}