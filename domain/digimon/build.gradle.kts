plugins {
    alias(libs.plugins.digimonadventure.jvm.library)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(projects.core.domain)

    compileOnly(
        libs.compose.stable.marker,
    )
}
