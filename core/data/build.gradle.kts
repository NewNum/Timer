
plugins {
    alias(libs.plugins.huxh.android.library)
    alias(libs.plugins.huxh.android.library.jacoco)
    alias(libs.plugins.huxh.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.huxh.apps.core.data"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.common)
    api(projects.core.database)
    api(projects.core.datastore)

    implementation(projects.core.notifications)

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlinx.serialization.json)
}
