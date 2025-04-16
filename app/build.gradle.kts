plugins {
    alias(libs.plugins.digimonadvencture.android.application)
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

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.ui.home)
    implementation(projects.ui.detail)
    implementation(projects.ui.bookmark)

    implementation(projects.presentation.home)
    implementation(projects.presentation.detail)
    implementation(projects.presentation.bookmark)

    implementation(projects.domain.home)
    implementation(projects.domain.detail)
    implementation(projects.domain.bookmark)

    implementation(projects.data.home)
    implementation(projects.data.detail)
    implementation(projects.data.bookmark)

    implementation(projects.core.ui.designsystem)

    implementation(projects.remote.home)
    implementation(projects.remote.detail)

    implementation(projects.local.bookmark)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtimeKtx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}