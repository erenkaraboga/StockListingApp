package com.example.stocklistingapp.util.firebase_analytics

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "LOG_EVENT"
@Singleton
class AnalyticsLogger @Inject constructor() : AnalyticsHelper {
    override fun logEvent(event: AnalyticsEvent) {
        Log.d(TAG, "Received analytics event: $event")
    }
}
