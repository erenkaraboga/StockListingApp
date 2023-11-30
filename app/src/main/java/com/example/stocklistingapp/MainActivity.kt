package com.example.stocklistingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.stocklistingapp.presentation.NavGraphs
import com.example.stocklistingapp.ui.theme.MyApplicationTheme
import com.example.stocklistingapp.common.extensions.LocalAnalyticsHelper
import com.example.stocklistingapp.common.extensions.logScreenView
import com.example.stocklistingapp.domain.model.AnalyticsHelper
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var analyticsHelper: AnalyticsHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            CompositionLocalProvider(LocalAnalyticsHelper provides analyticsHelper) {
                MyApplicationTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        analyticsHelper.logScreenView("MainActivity")
                        DestinationsNavHost(navGraph = NavGraphs.root)
                    }
                }
            }
        }

    }

}

