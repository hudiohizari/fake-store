import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

val properties = Properties()
file("../config.properties").inputStream().use { properties.load(it) }
android {
    namespace = "com.hizari.common"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        buildConfigField("String", "BASE_URL", properties["BASE_URL"].toString())

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

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}