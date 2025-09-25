plugins {
    alias(libs.plugins.digimonadventure.android.library)
    alias(libs.plugins.digimonadventure.android.room)
    alias(libs.plugins.digimonadventure.android.hilt)
}

android {
    namespace = "com.dis.local.digimon"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
    }
}

dependencies {
    implementation(projects.data.digimon)
    implementation(projects.core.database)
    implementation(libs.kotlinx.serialization.json)
}
