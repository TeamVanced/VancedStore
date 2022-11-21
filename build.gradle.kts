// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.3.1" apply false
    kotlin("android") version "1.7.20" apply false
    kotlin("plugin.serialization") version "1.7.20" apply false
    id("com.google.devtools.ksp") version "1.7.20-1.0.8" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}