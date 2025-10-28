plugins {
    alias(libs.plugins.digimonadventure.hilt.core)
    alias(libs.plugins.digimonadventure.jvm.library)
}

dependencies {
    implementation(projects.domain.digimon)
    implementation(projects.core.domain)

    implementation(libs.kotlinx.coroutines.core)
}
