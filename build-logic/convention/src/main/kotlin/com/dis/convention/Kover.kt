package com.dis.convention

import kotlinx.kover.gradle.plugin.dsl.KoverProjectExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

private const val MIN_LINE_COVERAGE = 90

private val generatedCoverageExclusions = listOf(
    "*.BuildConfig",
    "*.Manifest",
    "*.Manifest*",
    "*.R",
    "*.R$*",
    "*_Factory",
    "*_Factory$*",
    "*_MembersInjector",
    "*_MembersInjector$*",
    "*_HiltModules*",
    "hilt_aggregated_deps.*",
    "*ComposableSingletons*",
)

internal fun Project.configureKover() {
    extensions.configure<KoverProjectExtension> {
        reports {
            filters {
                excludes {
                    // Generated Android, Hilt, and Compose artifacts should not affect coverage gates.
                    classes(generatedCoverageExclusions)
                }
            }

            verify {
                rule("Minimum line coverage") {
                    minBound(MIN_LINE_COVERAGE)
                }
            }
        }
    }
}
