plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.google.dagger.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.symbol.processing)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.hizari.domain"
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.dagger.hilt)
    implementation(libs.kotlinx.serialization)

    ksp(libs.dagger.hilt.compiler)

    testImplementation(libs.cash.turbine)
    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}