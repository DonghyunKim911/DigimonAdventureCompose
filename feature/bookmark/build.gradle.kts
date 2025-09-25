plugins {
    alias(libs.plugins.digimonadventure.android.feature)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.dis.feature.bookmark"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
    }
}

dependencies {
    implementation(projects.presentation.bookmark)

    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}