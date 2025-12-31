plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.serialization)
}

android {
    namespace = "dev.georgiys.changerrgb.android"
    compileSdk = 36
    defaultConfig {
        applicationId = "dev.georgiys.changerrgb.android"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
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
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.compose.material)
    implementation(libs.androidx.activity.compose)
    implementation(libs.koin.androidx)
    implementation(libs.google.accompanist.systemuicontroller)
    implementation(libs.google.accompanist.permissions)
    implementation(libs.google.accompanist.refresh)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material.icons.core)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.com.patrykandpatrick.vico.compose)
    implementation(libs.com.patrykandpatrick.vico.compose.m3)
    implementation(libs.com.patrykandpatrick.vico.core)
    debugImplementation(libs.compose.ui.tooling)
}