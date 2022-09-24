package com.example.data.db.schema

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

/*
* teacher table
* */
object NotificationTable : Table("notification") {
    val id = integer("id").autoIncrement()
    val userId = integer("user_id").references(UserTable.id) //foreign key to teacher
    val title = text("title")
    val note = text("note")
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    val updatedAt = datetime("updated_at").clientDefault { LocalDateTime.now() }

    override val primaryKey = PrimaryKey(id)
}