package com.dis.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

fun DependencyHandlerScope.adUiLayerDependencies(project: Project) {

    "implementation"(project(":core:designsystem"))
    "implementation"(project(":core:ui"))


}