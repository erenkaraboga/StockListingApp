package com.example.stocklistingapp.domain.repository

import com.example.stocklistingapp.domain.model.NotificationModel
import okhttp3.ResponseBody
import retrofit2.Response

interface NotificationRepository {
    suspend fun postNotification(notification: NotificationModel) : Response<ResponseBody>
}

