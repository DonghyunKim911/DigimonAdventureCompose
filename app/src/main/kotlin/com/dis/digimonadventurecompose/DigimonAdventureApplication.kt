package com.dis.digimonadventurecompose

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class DigimonAdventureApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}
