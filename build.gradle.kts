import org.jlleitschuh.gradle.ktlint.KtlintExtension

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.kotlin.ktlint) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.android.test) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.hilt.plugin) apply false
    alias(libs.plugins.room) apply false
}

allprojects {
    apply {
        plugin(
            rootProject.libs.plugins.kotlin.ktlint
                .get()
                .pluginId,
        )
    }

    extensions.configure<KtlintExtension> {
        version.set(
            rootProject.libs.versions.kotlin.ktlint.engine
                .get(),
        )
        android.set(true)
        verbose.set(true)
        ignoreFailures.set(true)
    }
}
