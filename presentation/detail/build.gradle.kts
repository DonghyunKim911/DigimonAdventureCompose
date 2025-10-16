plugins {
    alias(libs.plugins.digimonadventure.android.library)
    alias(libs.plugins.digimonadventure.android.hilt)
}

android {
    namespace = "com.dis.presentation.detail"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
    }
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.presentation)
    implementation(projects.domain.digimon)

    implementation(libs.kotlinx.immutable.collection)
    implementation(libs.timber)

    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
