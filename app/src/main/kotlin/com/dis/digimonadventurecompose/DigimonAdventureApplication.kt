package com.dis.digimonadventurecompose

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DigimonAdventureApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("TEST", "DigimonAdventureApplication")
    }

}
