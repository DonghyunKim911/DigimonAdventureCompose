pluginManagement {
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

rootProject.name = "DigimonAdventureCompose"
include(":app")
include(":ui:home")
include(":ui:bookmark")
include(":presentation:home")
include(":presentation:bookmark")
include(":ui:detail")
include(":presentation:detail")
include(":domain:home")
include(":domain:detail")
include(":domain:bookmark")
include(":data:home")
include(":data:detail")
include(":data:bookmark")
include(":remote:home")
include(":remote:detail")
include(":local:bookmark")
include(":core:ui:designsystem")
