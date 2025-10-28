plugins {
    alias(libs.plugins.digimonadventure.jvm.library)

    alias(libs.plugins.digimonadventure.hilt.core)
}

dependencies {
    implementation(projects.domain.digimon)
    implementation(projects.core.domain)

    implementation(libs.kotlinx.coroutines.core)
}
