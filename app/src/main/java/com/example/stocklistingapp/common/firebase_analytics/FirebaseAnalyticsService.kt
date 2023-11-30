package com.example.stocklistingapp.common.firebase_analytics

import android.util.Log
import com.example.stocklistingapp.domain.model.AnalyticsEventModel
import com.example.stocklistingapp.domain.model.AnalyticsHelper
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import javax.inject.Inject

class FirebaseAnalyticsService @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics,
) : AnalyticsHelper {

    override fun logEvent(event: AnalyticsEventModel) {
        firebaseAnalytics.logEvent(event.type) {
            for (extra in event.extras) {
                param(
                    key = extra.key.take(40),
                    value = extra.value.take(100),
                )
            }
        }
        Log.d("STOCK_LISTING_APP", "Received analytics event: $event")
    }
}
