package com.example.data.db.schema

import org.jetbrains.exposed.sql.Table

object StudentBatchTable : Table("student_batch") {
    val id = integer("id").autoIncrement()
    val studentId = integer("student_id").references(StudentTable.id)
    val batchId = integer("batch_id").references(BatchTable.id)
    override val primaryKey = PrimaryKey(id)
}