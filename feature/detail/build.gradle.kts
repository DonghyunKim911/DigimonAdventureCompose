plugins {
    alias(libs.plugins.digimonadventure.android.feature)
    alias(libs.plugins.digimonadventure.android.hilt)
}

android {
    namespace = "com.dis.feature.detail"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
    }
}

dependencies {
    implementation(projects.presentation.detail)
    implementation(projects.core.presentation)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.viewModelCompose)

    implementation(libs.kotlinx.immutable.collection)

    implementation(libs.timber)

    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
