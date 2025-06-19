
plugins {
    alias(libs.plugins.huxh.android.library)
    alias(libs.plugins.huxh.android.library.jacoco)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.huxh.apps.core.domain"
}

dependencies {
    api(projects.core.data)
    api(projects.core.model)

    implementation(libs.javax.inject)

}