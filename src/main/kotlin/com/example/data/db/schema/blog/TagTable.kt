package com.example.data.db.schema.blog

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object TagTable : Table("blog_tags") {
    val id = integer("id").autoIncrement()
    val name = text("name").uniqueIndex()
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    val updatedAt = datetime("updated_at").clientDefault { LocalDateTime.now() }

    override val primaryKey = PrimaryKey(id)
}