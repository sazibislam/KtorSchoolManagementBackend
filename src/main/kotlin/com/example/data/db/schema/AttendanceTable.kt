package com.example.data.db.schema

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object AttendanceTable : Table("attendance") {
    val id = integer("id").autoIncrement()
    val batchId = integer("batch_id").references(BatchTable.id)
    val studentId = integer("student_id").references(StudentTable.id)
    val isPresent = bool("is_present").clientDefault { false }
    val date = datetime("date").clientDefault { LocalDateTime.now() }
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    val updatedAt = datetime("updated_at").clientDefault { LocalDateTime.now() }

    override val primaryKey = PrimaryKey(id)
}