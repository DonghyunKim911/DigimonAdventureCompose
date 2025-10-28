plugins {
    alias(libs.plugins.digimonadventure.android.application.compose)
    alias(libs.plugins.digimonadventure.android.hilt)
}

android {
    namespace = "com.dis.digimonadventurecompose"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        applicationId = "com.dis.digimonadventurecompose"
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.feature.splash)
    implementation(projects.feature.main)
    implementation(projects.feature.home)
    implementation(projects.feature.detail)
    implementation(projects.feature.bookmark)

    implementation(projects.presentation.home)
    implementation(projects.presentation.detail)
    implementation(projects.presentation.bookmark)

    implementation(projects.domain.digimon)
    implementation(projects.data.digimon)
    implementation(projects.remote.digimon)
    implementation(projects.local.digimon)

    implementation(projects.core.designsystem)
    implementation(projects.core.navigation)
    implementation(projects.core.network)
    implementation(projects.core.ui)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeKtx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.hilt.android)
    implementation(libs.androidx.startup)

    implementation(libs.timber)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}