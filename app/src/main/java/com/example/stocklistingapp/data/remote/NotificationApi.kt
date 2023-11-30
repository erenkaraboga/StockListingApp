package com.example.stocklistingapp.data.remote

import com.example.stocklistingapp.domain.model.NotificationModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface NotificationApi {

    @POST("fcm/send")
    @Headers("Authorization: key=${SERVER_KEY}", "Content-Type:$CONTENT_TYPE")
    suspend fun postNotification(
        @Body notification: NotificationModel
    ): Response<ResponseBody>

    companion object {
        const val  SERVER_KEY = "AAAA6lV14cg:APA91bFGEJYcUMnQrvZ9nv2uCCPpzxYrw05UElVnzhCVKCQzyzD2frPUbrUtRWF_naQfJ2hKtlSphwOKhCiUjjzxt5igwJhNOnA4pFyesMbYqCQrJ4p9EseC0sQuRY0cn7_Do5TC21oO"
        const val BASE_URL = "https://fcm.googleapis.com"
        private const val CONTENT_TYPE = "application/json"
    }
}