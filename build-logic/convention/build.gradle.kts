plugins {
    `kotlin-dsl`
}

group = "com.dis.digimonadventurecompose.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.compose.compiler.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.kotlinx.serialization.json)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = libs.plugins.digimonadvencture.android.application.get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
    }
}
