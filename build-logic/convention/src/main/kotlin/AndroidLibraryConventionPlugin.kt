import com.android.build.api.dsl.LibraryExtension
import com.dis.convention.ExtensionType
import com.dis.convention.configureBuildTypes
import com.dis.convention.configureCommonUnitTestDependencies
import com.dis.convention.configureKover
import com.dis.convention.configureKotlinAndroid
import com.dis.convention.configureUnitTestPlatform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")
            apply(plugin = "org.jetbrains.kotlin.android")
            apply(plugin = "org.jetbrains.kotlinx.kover")

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)

                configureBuildTypes(
                    commonExtension = this,
                    extensionType = ExtensionType.LIBRARY,
                )

                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro")
                }
            }

            configureKover()
            configureUnitTestPlatform()
            configureCommonUnitTestDependencies()
        }
    }
}
