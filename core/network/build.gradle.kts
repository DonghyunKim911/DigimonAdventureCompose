plugins {
    alias(libs.plugins.digimonadventure.android.library)
    alias(libs.plugins.digimonadventure.android.hilt)
    alias(libs.plugins.digimonadventure.android.retrofit)
}

android {
    namespace = "com.dis.core.network"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        val apiUrl = rootProject.findProperty("DIGIMON_BASE_URL") as String

        getByName("debug") {
            isMinifyEnabled = false
            buildConfigField("String", "DIGIMON_BASE_URL", "\"$apiUrl\"")
        }

        getByName("release") {
            isMinifyEnabled = true
            buildConfigField("String", "DIGIMON_BASE_URL", "\"$apiUrl\"")
        }

    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

}
