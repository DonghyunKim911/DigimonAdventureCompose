plugins {
    alias(libs.plugins.digimonadventure.android.library)
    alias(libs.plugins.digimonadventure.android.hilt)
}

android {
    namespace = "com.dis.presentation.bookmark"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
    }
}

dependencies {
    implementation(projects.domain.digimon)
    implementation(projects.core.presentation)
    implementation(projects.core.domain)
    implementation(libs.javax.inject)

    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.kotlinx.immutable.collection)

    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
