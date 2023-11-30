
package com.example.stocklistingapp.util

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.stocklistingapp.util.firebase_analytics.AnalyticsEvent
import com.example.stocklistingapp.util.firebase_analytics.AnalyticsHelper
import com.example.stocklistingapp.util.firebase_analytics.AnalyticsLogger

val LocalAnalyticsHelper = staticCompositionLocalOf<AnalyticsHelper> {
    AnalyticsLogger()
}

fun AnalyticsHelper.logScreenView(screenName: String) {
    logEvent(
        AnalyticsEvent(
            type = AnalyticsEvent.Types.SCREEN_VIEW,
            extras = listOf(
                AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.SCREEN_NAME, screenName),
            ),
        ),
    )
}

fun AnalyticsHelper.buttonClick(screenName: String) {
    logEvent(
        AnalyticsEvent(
            type = AnalyticsEvent.Types.BUTTON_CLICK,
            extras = listOf(
                AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.BUTTON_ID, screenName),
            ),
        ),
    )
}
fun AnalyticsHelper.selectCompany(companyName: String) {
    logEvent(
        AnalyticsEvent(
            type = AnalyticsEvent.Types.SELECT_ITEM,
            extras = listOf(
                AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.ITEM_ID, companyName),
            ),
        ),
    )
}
