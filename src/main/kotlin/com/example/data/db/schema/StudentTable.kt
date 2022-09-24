package com.example.data.db.schema

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime
/*
* teacher table
* */
object StudentTable: Table("student") {
    val id = integer("id").autoIncrement()
    val classId = integer("class_id") //foreign id to class
    val subjectId = integer("subject_id") //foreign id to subject
    val instituteId = integer("institute_id") //foreign id to institute
    val fullName = varchar("full_name", 256)
    val fatherName = varchar("father_name", 256)
    val studentId = text("student_id")
    val avatar = text("avatar")
    val phone = text("phone")
    val address = text("address")
    val email = varchar("email", 256)
    val password = text("password")
    val dob = datetime("dob").clientDefault { LocalDateTime.now() }
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    val updatedAt = datetime("updated_at").clientDefault { LocalDateTime.now() }

    override val primaryKey = PrimaryKey(id)
}