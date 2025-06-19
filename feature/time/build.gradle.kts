
plugins {
    alias(libs.plugins.huxh.android.feature)
    alias(libs.plugins.huxh.android.library.compose)
    alias(libs.plugins.huxh.android.library.jacoco)
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.huxh.apps.feature.time"
}

dependencies {
    implementation(libs.accompanist.permissions)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.notifications)

    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.robolectric)

    androidTestImplementation(libs.bundles.androidx.compose.ui.test)
}
