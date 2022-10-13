package com.example.data.db.schema.blog

import org.jetbrains.exposed.sql.Table

object PostsReachTable : Table("blog_post_reach") {
    val id = integer("id").autoIncrement()
    val postId = integer("post_id").references(PostsTable.id)
    val reach = integer("reach")
    override val primaryKey = PrimaryKey(id)
}