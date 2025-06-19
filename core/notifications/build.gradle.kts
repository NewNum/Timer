
plugins {
    alias(libs.plugins.huxh.android.library)
    alias(libs.plugins.huxh.hilt)
}

android {
    namespace = "com.huxh.apps.core.notifications"
}

dependencies {
    api(projects.core.model)

    implementation(projects.core.common)

    compileOnly(platform(libs.androidx.compose.bom))
}
