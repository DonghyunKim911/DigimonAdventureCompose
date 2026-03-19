import com.dis.convention.configureCommonUnitTestDependencies
import com.dis.convention.configureKover
import com.dis.convention.configureKotlinJvm
import com.dis.convention.configureUnitTestPlatform
import com.dis.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class JvmLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.jvm")
            apply(plugin = "org.jetbrains.kotlinx.kover")

            configureKotlinJvm()
            configureKover()
            configureUnitTestPlatform()
            configureCommonUnitTestDependencies()

            dependencies {
                "implementation"(libs.findLibrary("javax-inject").get())
            }
        }
    }
}
