import com.android.build.gradle.TestExtension
import com.huxh.apps.timer.configureGradleManagedDevices
import com.huxh.apps.timer.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.test")
            apply(plugin = "org.jetbrains.kotlin.android")

            extensions.configure<TestExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 35
                configureGradleManagedDevices(this)
            }
        }
    }
}
