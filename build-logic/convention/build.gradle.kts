import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
    alias(libs.plugins.android.lint)
}

group = "com.huxh.apps.timer.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.firebase.crashlytics.gradlePlugin)
    compileOnly(libs.firebase.performance.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
    implementation(libs.truth)
    lintChecks(libs.androidx.lint.gradle)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = libs.plugins.huxh.android.application.compose.get().pluginId
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = libs.plugins.huxh.android.application.asProvider().get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationJacoco") {
            id = libs.plugins.huxh.android.application.jacoco.get().pluginId
            implementationClass = "AndroidApplicationJacocoConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = libs.plugins.huxh.android.library.compose.get().pluginId
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.huxh.android.library.asProvider().get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = libs.plugins.huxh.android.feature.get().pluginId
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidLibraryJacoco") {
            id = libs.plugins.huxh.android.library.jacoco.get().pluginId
            implementationClass = "AndroidLibraryJacocoConventionPlugin"
        }
        register("androidTest") {
            id = libs.plugins.huxh.android.test.get().pluginId
            implementationClass = "AndroidTestConventionPlugin"
        }
        register("hilt") {
            id = libs.plugins.huxh.hilt.get().pluginId
            implementationClass = "HiltConventionPlugin"
        }
        register("androidRoom") {
            id = libs.plugins.huxh.android.room.get().pluginId
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("androidFirebase") {
            id = libs.plugins.huxh.android.application.firebase.get().pluginId
            implementationClass = "AndroidApplicationFirebaseConventionPlugin"
        }
        register("androidFlavors") {
            id = libs.plugins.huxh.android.application.flavors.get().pluginId
            implementationClass = "AndroidApplicationFlavorsConventionPlugin"
        }
        register("androidLint") {
            id = libs.plugins.huxh.android.lint.get().pluginId
            implementationClass = "AndroidLintConventionPlugin"
        }
        register("jvmLibrary") {
            id = libs.plugins.huxh.jvm.library.get().pluginId
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}
