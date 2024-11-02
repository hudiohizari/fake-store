import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.google.dagger.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.symbol.processing)
    alias(libs.plugins.kotlinx.serialization)
}

val properties = Properties()
file("../config.properties").inputStream().use { properties.load(it) }
android {
    namespace = "com.hizari.fakestore"
    compileSdk = 35


    defaultConfig {
        applicationId = "com.hizari.fakestore"
        minSdk = 24
        targetSdk = 35

        val major = 1
        val minor = 0
        val patch = 0
        val build = 0

        /**
         * The versionCode is generated using a weighted system to ensure it always increments with version changes.
         * This prevents issues where resetting the build number could result in a smaller versionCode.
         * Major, minor, patch, and build numbers are weighted as follows:
         * - Major: *1,000,000 (ensures major changes result in significant versionCode increases)
         * - Minor: *10,000 (smaller increments than major, but larger than patch/build)
         * - Patch: *100 (increments for bug fixes or small updates)
         * - Build: *1 (smallest increment, typically for build numbers or CI builds)
         * This ensures that the versionCode always increases regardless of which version component is updated.
         */
        versionCode = (major * 1000000) + (minor * 10000) + (patch * 100) + build
        versionName = "$major.$minor.$patch.$build"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "environment"
    productFlavors {
        create("development") {
            dimension = "environment"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            resValue("string", "app_name", properties["DEV_APP_NAME"].toString())
        }
        create("production") {
            dimension = "environment"
            resValue("string", "app_name", properties["PROD_APP_NAME"].toString())
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
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.fonts)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icon.extended)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.coil.compose)
    implementation(libs.dagger.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.kotlinx.serialization)
    implementation(libs.navigation.compose)
    implementation(platform(libs.androidx.compose.bom))

    ksp(libs.dagger.hilt.compiler)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}