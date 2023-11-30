package com.example.stocklistingapp.common.firebase_analytics

import android.util.Log
import com.example.stocklistingapp.domain.model.AnalyticsEventModel
import com.example.stocklistingapp.domain.model.AnalyticsHelper
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "LOG_EVENT"
@Singleton
class AnalyticsLogger @Inject constructor() : AnalyticsHelper {
    override fun logEvent(event: AnalyticsEventModel) {
        Log.d(TAG, "Received analytics event: $event")
    }
}
