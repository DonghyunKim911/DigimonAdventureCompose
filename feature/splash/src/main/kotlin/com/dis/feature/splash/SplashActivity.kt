package com.dis.feature.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.dis.core.navigation.Route
import com.dis.core.navigation.startActivity
import com.dis.core.ui.designsystem.theme.DigimonAdventureComposeTheme
import com.dis.feature.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            DigimonAdventureComposeTheme {
                Route.Splash.startActivity(this, MainActivity::class.java)
            }
        }
    }

}
