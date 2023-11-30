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
        const val  SERVER_KEY = "JEJeRzjZ_8-orrcBl09-AFFjNXXcYOROZ2dnyyA2h2Q"

        const val BASE_URL = "https://fcm.googleapis.com"
        private const val CONTENT_TYPE = "application/json"
    }
}