plugins {
    alias(libs.plugins.digimonadventure.android.library)
    alias(libs.plugins.digimonadventure.android.hilt)
    alias(libs.plugins.digimonadventure.android.retrofit)
}

android {
    namespace = "com.dis.remote.digimon"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
    }
}

dependencies {
    implementation(projects.data.digimon)
    implementation(projects.core.network)
}
