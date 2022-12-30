package com.example.data.db.schema

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object TaskTable : Table("tasks") {
    val id = integer("id").autoIncrement()
    val userId = integer("user_id").references(UserTable.id)
    val taskName = text("task_name")
    val taskDetails = text("details")
    val taskLocation = text("location")
    val isCompleted = bool("is_completed").clientDefault { false }
    val completedAt = datetime("completed_at")
    val startAt = datetime("start_at")
    val endAt = datetime("end_at")
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    val updatedAt = datetime("updated_at").clientDefault { LocalDateTime.now() }

    override val primaryKey = PrimaryKey(id)
}