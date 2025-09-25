plugins {
    alias(libs.plugins.digimonadventure.android.library)
    alias(libs.plugins.digimonadventure.android.hilt)
}

android {
    namespace = "com.core.presentation"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
    }
}

dependencies {
    implementation(libs.androidx.lifecycle.viewModelCompose)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}