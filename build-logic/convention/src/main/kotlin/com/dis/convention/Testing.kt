package com.dis.convention

import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

internal fun Project.configureUnitTestPlatform() {
    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}

internal fun Project.configureCommonUnitTestDependencies() {
    dependencies {
        "testImplementation"(libs.findLibrary("kotlin-test").get())
        "testImplementation"(libs.findLibrary("junit").get())
        "testImplementation"(libs.findLibrary("kotlinx-coroutines-test").get())
        "testImplementation"(libs.findLibrary("kotest-runner-junit5").get())
        "testImplementation"(libs.findLibrary("kotest-assertions-core").get())
        "testImplementation"(libs.findLibrary("turbine").get())
        "testImplementation"(libs.findLibrary("mockk").get())
        "testRuntimeOnly"(libs.findLibrary("junit-vintage-engine").get())
    }
}
