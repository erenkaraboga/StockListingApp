package com.example.stocklistingapp.common.extensions

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.stocklistingapp.domain.model.AnalyticsEventModel
import com.example.stocklistingapp.domain.model.AnalyticsHelper
import com.example.stocklistingapp.common.firebase_analytics.AnalyticsLogger

val LocalAnalyticsHelper = staticCompositionLocalOf<AnalyticsHelper> {
    AnalyticsLogger()
}

fun AnalyticsHelper.logScreenView(screenName: String) {
    logEvent(
        AnalyticsEventModel(
            type = AnalyticsEventModel.Types.SCREEN_VIEW,
            extras = listOf(
                AnalyticsEventModel.Param(AnalyticsEventModel.ParamKeys.SCREEN_NAME, screenName),
            ),
        ),
    )
}

fun AnalyticsHelper.buttonClick(screenName: String) {
    logEvent(
        AnalyticsEventModel(
            type = AnalyticsEventModel.Types.BUTTON_CLICK,
            extras = listOf(
                AnalyticsEventModel.Param(AnalyticsEventModel.ParamKeys.BUTTON_ID, screenName),
            ),
        ),
    )
}
