
plugins {
    alias(libs.plugins.huxh.jvm.library)
    alias(libs.plugins.huxh.hilt)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}