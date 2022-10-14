package com.example.data.db

import com.example.data.db.extention.InsertOrUpdate
import com.example.data.db.schema.NotificationTable
import com.example.data.db.schema.UserTable
import com.example.data.db.schema.blog.*
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init() {
        Database.connect(hikari())
        transaction {
            SchemaUtils.create(UserTable)
            SchemaUtils.create(NotificationTable)
            SchemaUtils.create(
                PostTable, PostDescriptionTable, PostCommentTable, PostReachTable, PostTagTable, TagTable
            )
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.postgresql.Driver"
        config.jdbcUrl = "jdbc:postgresql://127.0.0.1:5432/postgres?user=postgres&password=sazib"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: () -> T): T = withContext(Dispatchers.IO) {
        transaction {
            block()
        }
    }

    /*
    *    Example:
    *    PostReachTable.insertOrUpdate(PostReachTable.reach) {
            it[postId] = id
            it[reach] = 2
        }
        * */
    suspend fun <T : Table> T.insertOrUpdate(
        vararg onDuplicateUpdateKeys: Column<*>, body: T.(InsertStatement<Number>) -> Unit
    ) = withContext(Dispatchers.IO) {
        InsertOrUpdate<Number>(onDuplicateUpdateKeys, this@insertOrUpdate).apply {
            body(this)
            execute(TransactionManager.current())
        }
    }
}