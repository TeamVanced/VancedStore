import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

android {
    namespace = "com.vanced.store"

    compileSdk = 33

    defaultConfig {
        applicationId = "com.vanced.store"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.compilerVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
        optIn("androidx.compose.material3.ExperimentalMaterial3Api")
        optIn("androidx.compose.animation.ExperimentalAnimationApi")
        optIn("androidx.compose.foundation.ExperimentalFoundationApi")
    }
}

fun KotlinJvmOptions.optIn(library: String) {
    freeCompilerArgs = freeCompilerArgs +
            "-opt-in=$library"
}

dependencies {
    Dependencies.Ktor.applyDependencies(this)
    Dependencies.AndroidxCore.applyDependencies(this)
    Dependencies.AndroidxPreferences.applyDependencies(this)
    Dependencies.AndroidxRoom.applyDependencies(this)
    Dependencies.Compose.applyDependencies(this)
    Dependencies.Accompanist.applyDependencies(this)
    Dependencies.Koin.applyDependencies(this)
}