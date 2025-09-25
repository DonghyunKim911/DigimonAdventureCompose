plugins {
    alias(libs.plugins.digimonadventure.android.feature)
    alias(libs.plugins.digimonadventure.android.hilt)
}

android {
    namespace = "com.dis.feature.home"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
    }
}

dependencies {
    implementation(projects.presentation.home)
    implementation(projects.core.presentation)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.kotlinx.immutable.collection)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.viewModelCompose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}