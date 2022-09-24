package com.example.data.db.schema

import com.example.data.db.schema.TaskTable.references
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

/*
* teacher table
* */
object StudentBatchTable : Table("batch") {
    val id = integer("id").autoIncrement()
    val teacherId = integer("teacher_id").references(UserTable.id)
    val studentIdList = text("student_id_list")
    val name = text("name")
    val details = text("details")
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    val updatedAt = datetime("updated_at").clientDefault { LocalDateTime.now() }

    override val primaryKey = PrimaryKey(id)
}