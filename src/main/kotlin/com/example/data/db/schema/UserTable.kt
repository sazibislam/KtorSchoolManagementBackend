package com.example.data.db.schema

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

/*
* teacher table
* */
object UserTable : Table("users") {
    val id = integer("id").autoIncrement()
    val instituteId = integer("institute_id") //foreign id to institute
    val fullName = varchar("full_name", 256)
    val designation = varchar("designation", 256)
    val avatar = text("avatar")
    val isActive = bool("is_active").clientDefault { true }
    val phone = text("phone")
    val email = varchar("email", 256)
    val password = text("password")
    val address = text("address")
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    val updatedAt = datetime("updated_at").clientDefault { LocalDateTime.now() }

    override val primaryKey = PrimaryKey(id)
}