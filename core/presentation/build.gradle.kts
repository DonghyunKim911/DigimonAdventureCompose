plugins {
    alias(libs.plugins.digimonadventure.android.library)
    alias(libs.plugins.digimonadventure.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.core.presentation"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
    }
}

dependencies {
    implementation(projects.core.domain)

    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.kotlinx.immutable.collection)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
