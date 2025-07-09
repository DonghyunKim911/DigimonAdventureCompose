import com.dis.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidHiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "dagger.hilt.android.plugin")
            apply(plugin = "com.google.devtools.ksp")

            extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                "implementation"(libs.findLibrary("hilt.android").get())
                "implementation"(libs.findLibrary("androidx.hilt.navigation.compose").get())
                "ksp"(libs.findLibrary("hilt.compiler").get())
            }

        }
    }

}
