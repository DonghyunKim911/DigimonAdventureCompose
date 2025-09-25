plugins {
    alias(libs.plugins.digimonadventure.hilt.core)
    alias(libs.plugins.digimonadventure.jvm.library)
}

dependencies {
    implementation(projects.domain.digimon)

    implementation(libs.kotlinx.coroutines.core)
}
