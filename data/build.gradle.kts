plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.google.dagger.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.symbol.processing)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.hizari.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
        )
    }

    flavorDimensions += "environment"
    productFlavors {
        create("development") {
            dimension = "environment"
        }
        create("production") {
            dimension = "environment"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))

    debugImplementation(libs.chucker.debug)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.datastore)
    implementation(libs.dagger.hilt)
    implementation(libs.kotlinx.serialization)
    implementation(libs.okhttp.interceptor)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)

    ksp(libs.dagger.hilt.compiler)

    releaseImplementation(libs.chucker.release)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}