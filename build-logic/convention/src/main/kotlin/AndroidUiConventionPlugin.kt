import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidUiConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "digimonadventure.android.library.compose")

            dependencies {
                "implementation"(project(":core:designsystem"))
                "implementation"(project(":core:ui"))
            }
        }
    }

}
