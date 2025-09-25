pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "DigimonAdventureCompose"

include(":app")
include(":feature:splash")
include(":feature:main")
include(":feature:home")
include(":feature:bookmark")
include(":feature:detail")
include(":presentation:home")
include(":presentation:bookmark")
include(":presentation:detail")
include(":domain:digimon")
include(":data:digimon")
include(":remote:digimon")
include(":local:digimon")
include(":core:designsystem")
include(":core:ui")
include(":core:database")
include(":core:navigation")
include(":core:network")
include(":core:presentation")
