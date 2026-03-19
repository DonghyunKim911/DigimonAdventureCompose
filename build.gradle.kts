import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.kotlin.dsl.configure
import org.jlleitschuh.gradle.ktlint.KtlintExtension

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.kover) apply false
    alias(libs.plugins.kotlin.ktlint) apply false
    alias(libs.plugins.detekt) apply false
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
        plugin(
            rootProject.libs.plugins.detekt
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

    extensions.configure<DetektExtension> {
        toolVersion = rootProject.libs.versions.detekt.get()
        buildUponDefaultConfig = true
        allRules = false
        autoCorrect = false
        parallel = true
        basePath = rootDir.absolutePath
        config.setFrom("$rootDir/config/detekt/detekt.yml")
        ignoreFailures = true
    }

    dependencies {
        add("detektPlugins", rootProject.libs.detekt.formatting)
    }

    tasks.withType<Detekt>().configureEach {
        jvmTarget = "21"
        reports {
            html.required.set(true)
            xml.required.set(true)
            sarif.required.set(true)
            txt.required.set(false)
            md.required.set(false)
        }
    }
}
