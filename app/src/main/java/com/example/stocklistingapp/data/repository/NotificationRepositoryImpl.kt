package com.example.stocklistingapp.data.repository
import com.example.stocklistingapp.data.remote.NotificationApi
import com.example.stocklistingapp.domain.model.NotificationModel
import com.example.stocklistingapp.domain.repository.NotificationRepository
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NotificationRepositoryImpl @Inject constructor(
    val api: NotificationApi,

) : NotificationRepository {
    override suspend fun postNotification(notification: NotificationModel): Response<ResponseBody> {
        return api.postNotification(notification)
    }


}