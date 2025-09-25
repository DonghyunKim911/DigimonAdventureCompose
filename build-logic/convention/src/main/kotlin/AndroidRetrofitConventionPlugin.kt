import com.dis.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidRetrofitConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

            dependencies {
                "implementation"(platform(libs.findLibrary("retrofit-bom").get()))
                "implementation"(libs.findBundle("bundle.retrofit").get())
                "implementation"(platform(libs.findLibrary("okhttp-bom").get()))
                "implementation"(libs.findLibrary("kotlinx.serialization.json").get())
                "implementation"(libs.findLibrary("retrofit-kotlinx-serialization").get())
            }
        }
    }

}
