import com.android.build.api.dsl.ApplicationExtension
import com.dis.convention.ExtensionType
import com.dis.convention.configureBuildTypes
import com.dis.convention.configureKover
import com.dis.convention.configureCommonUnitTestDependencies
import com.dis.convention.configureKotlinAndroid
import com.dis.convention.configureUnitTestPlatform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlinx.kover")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)

                configureBuildTypes(
                    commonExtension = this,
                    extensionType = ExtensionType.APPLICATION,
                )
            }

            configureKover()
            configureUnitTestPlatform()
            configureCommonUnitTestDependencies()
        }
    }
}
