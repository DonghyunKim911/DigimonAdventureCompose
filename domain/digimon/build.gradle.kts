plugins {
    alias(libs.plugins.digimonadventure.jvm.library)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    compileOnly(
        libs.compose.stable.marker,
    )
}
