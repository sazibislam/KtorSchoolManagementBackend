package com.example.data.service.user

import com.example.data.model.Notification
import com.example.data.model.User


interface UserService {
    suspend fun getUser(id: Int): User
    suspend fun getAllNotification(id: Int): List<Notification>
    suspend fun deleteAllNotification(id: Int): Boolean
    suspend fun insertMocNotificationData(id: Int)

}