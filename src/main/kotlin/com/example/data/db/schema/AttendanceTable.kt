package com.example.data.db.schema

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

/*
* teacher table
* */
object AttendanceTable : Table("attendance") {
    val id = integer("id").autoIncrement()
    val teacherId = integer("teacher_id").references(UserTable.id) //foreign key to teacher
    val isPresent = bool("is_present").clientDefault { false }
    val note = text("note")
    val attendanceList = text("students_id_list")
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    val updatedAt = datetime("updated_at").clientDefault { LocalDateTime.now() }

    override val primaryKey = PrimaryKey(id)
}