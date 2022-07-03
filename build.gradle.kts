// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.2.1" apply false
    kotlin("android") version "1.7.0" apply false
    kotlin("plugin.serialization") version "1.7.0" apply false
    id("com.google.devtools.ksp") version "1.7.0-1.0.6" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}