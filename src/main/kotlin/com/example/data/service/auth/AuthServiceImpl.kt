package com.example.data.service.auth

import com.example.data.db.DatabaseFactory.dbQuery
import com.example.data.db.extention.toUser
import com.example.data.db.schema.UserTable
import com.example.data.model.User
import com.example.plugins.route.auth.CreateUserParams
import com.example.plugins.route.auth.UserParams
import com.example.plugins.security.hashing.hash
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.update

class AuthServiceImpl : AuthService {
    override suspend fun registerUser(params: CreateUserParams): User? {
        var statement: InsertStatement<Number>? = null
        dbQuery {
            statement = UserTable.insert {
                it[email] = params.email
                it[password] = hash(params.password)
                it[fullName] = params.fullName
                it[avatar] = params.avatar
                it[instituteId] = 0
                it[designation] = ""
                it[isActive] = true
                it[phone] = ""
                it[address] = ""
            }
        }
        return statement?.resultedValues?.get(0).toUser()
    }

    override suspend fun loginUser(email: String, password: String): User? {
        val hashedPassword = hash(password)
        val userRow = dbQuery {
            UserTable.select { UserTable.email eq email and (UserTable.password eq hashedPassword) }.firstOrNull()
        }
        return userRow.toUser()
    }

    override suspend fun findUserByEmail(email: String): User? {
        val user = dbQuery {
            UserTable.select { UserTable.email.eq(email) }
                .map { it.toUser() }.singleOrNull()
        }
        return user
    }

    override suspend fun updateUserCred(params: UserParams): User? {

        val updatedUser = dbQuery {
            UserTable.update({ UserTable.email eq params.email }) { table_ ->
                params.password?.let { pass_ ->
                    table_[password] = hash(pass_)
                }

                params.fullName?.let { name_ ->
                    table_[fullName] = name_
                }

                params.avatar?.let { avatar ->
                    table_[UserTable.avatar] = avatar
                }
            }

            UserTable.select { UserTable.email.eq(params.email) }
                .map { it.toUser() }.singleOrNull()
        }
        return updatedUser
    }
}