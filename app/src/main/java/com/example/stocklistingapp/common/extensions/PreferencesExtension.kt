package com.example.stocklistingapp.common.extensions

import android.content.SharedPreferences

object Preferences {

    const val PREFS = "firebaseCloudMessagingPrefs"
    const val TOKEN = "token"
    const val DEFAULT_TOKEN = ""
}

fun SharedPreferences.token() = getString(Preferences.TOKEN, Preferences.DEFAULT_TOKEN) ?: Preferences.DEFAULT_TOKEN