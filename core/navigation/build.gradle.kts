plugins {
    alias(libs.plugins.digimonadventure.android.library.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.dis.core.navigaiton"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
    }
}

dependencies {
    implementation(projects.core.presentation)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation3.runtime)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
