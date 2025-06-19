

plugins {
    alias(libs.plugins.huxh.android.library)
    alias(libs.plugins.huxh.android.library.jacoco)
    alias(libs.plugins.huxh.android.room)
    alias(libs.plugins.huxh.hilt)
}

android {
    namespace = "com.huxh.apps.core.database"
}

dependencies {
    api(projects.core.model)

    implementation(libs.kotlinx.datetime)

    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.kotlinx.coroutines.test)
}
