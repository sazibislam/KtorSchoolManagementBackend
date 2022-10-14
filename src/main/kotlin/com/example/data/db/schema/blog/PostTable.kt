package com.example.data.db.schema.blog

import com.example.data.db.schema.UserTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object PostTable : Table("blog_post") {
    val id = integer("id").autoIncrement()
    val userId = integer("user_id").references(UserTable.id)
    val title = text("title")
    val image = text("img")
    val slug = text("slug")
    val published = bool("published")
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    val updatedAt = datetime("updated_at").clientDefault { LocalDateTime.now() }

    override val primaryKey = PrimaryKey(id)
}