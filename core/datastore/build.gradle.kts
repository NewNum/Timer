

plugins {
    alias(libs.plugins.huxh.android.library)
    alias(libs.plugins.huxh.android.library.jacoco)
    alias(libs.plugins.huxh.hilt)
}

android {
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }
    namespace = "com.huxh.apps.core.datastore"
}

dependencies {
    api(libs.androidx.dataStore)
    api(projects.core.datastoreProto)
    api(projects.core.model)

    implementation(projects.core.common)

    testImplementation(libs.kotlinx.coroutines.test)
}
