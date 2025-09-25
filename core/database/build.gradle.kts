plugins {
    alias(libs.plugins.digimonadventure.android.library)
    alias(libs.plugins.digimonadventure.android.room)
    alias(libs.plugins.digimonadventure.android.hilt)
}

android {
    namespace = "com.dis.core.database"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
    }
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
