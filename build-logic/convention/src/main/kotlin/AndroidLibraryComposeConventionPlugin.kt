import com.android.build.api.dsl.LibraryExtension
import com.dis.convention.configureAndroidCompose
import com.dis.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "digimonadventure.android.library")

            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)

            dependencies {
                "implementation"(libs.findLibrary("material").get())
                "implementation"(libs.findLibrary("androidx-material3").get())
                "implementation"(libs.findLibrary("androidx-material-icons-extended").get())
                "implementation"(libs.findLibrary("androidx-navigation3-runtime").get())
                "implementation"(libs.findLibrary("androidx-navigation3-ui").get())
                "implementation"(libs.findLibrary("androidx-lifecycle-viewmodel-navigation3").get())
                "implementation"(libs.findLibrary("coil").get())
                "implementation"(libs.findLibrary("coil-network-okhttp").get())
            }
        }
    }

}
