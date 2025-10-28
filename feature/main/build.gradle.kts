plugins {
    alias(libs.plugins.digimonadventure.android.feature)
    alias(libs.plugins.digimonadventure.android.hilt)
}

android {
    namespace = "com.dis.feature.main"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
    }
}

dependencies {
    implementation(projects.presentation.home)
    implementation(projects.presentation.detail)
    implementation(projects.feature.home)
    implementation(projects.feature.detail)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
