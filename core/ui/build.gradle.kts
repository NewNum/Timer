
plugins {
    alias(libs.plugins.huxh.android.library)
    alias(libs.plugins.huxh.android.library.compose)
    alias(libs.plugins.huxh.android.library.jacoco)
}

android {
    namespace = "com.huxh.apps.core.ui"
}

dependencies {
    api(libs.androidx.metrics)
    api(projects.core.designsystem)
    api(projects.core.model)

    implementation(libs.androidx.browser)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)

    androidTestImplementation(libs.bundles.androidx.compose.ui.test)
}
