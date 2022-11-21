import org.gradle.kotlin.dsl.DependencyHandlerScope

sealed class Dependencies {

    object Ktor : Dependencies() {
        private const val version = "2.1.3"

        private const val ktorCore = "io.ktor:ktor-client-core:$version"
        private const val ktorAndroid = "io.ktor:ktor-client-android:$version"
        private const val ktorContentNegotiation = "io.ktor:ktor-client-content-negotiation:$version"
        private const val ktorSerializationJson = "io.ktor:ktor-serialization-kotlinx-json:$version"

        override fun applyDependencies(scope: DependencyHandlerScope) {
            scope {
                implementation(ktorCore)
                implementation(ktorAndroid)
                implementation(ktorContentNegotiation)
                implementation(ktorSerializationJson)
            }
        }
    }

    object AndroidxCore : Dependencies() {
        private const val coreVersion = "1.7.0"
        private const val coreSplashScreenVersion = "1.0.0-rc01"

        private const val core = "androidx.core:core:$coreVersion"
        private const val coreKtx = "androidx.core:core-ktx:$coreVersion"
        private const val coreSplashScreen = "androidx.core:core-splashscreen:$coreSplashScreenVersion"

        override fun applyDependencies(scope: DependencyHandlerScope) {
            scope {
                implementation(core)
                implementation(coreKtx)
                implementation(coreSplashScreen)
            }
        }
    }

    object AndroidxPreferences : Dependencies() {
        private const val version = "1.2.0"

        private const val preference = "androidx.preference:preference:$version"
        private const val preferenceKtx = "androidx.preference:preference-ktx:$version"

        private const val datastorePreferences = "androidx.datastore:datastore-preferences:1.0.0"

        override fun applyDependencies(scope: DependencyHandlerScope) {
            scope {
                implementation(preference)
                implementation(preferenceKtx)
                implementation(datastorePreferences)
            }
        }
    }

    object AndroidxRoom : Dependencies() {
        private const val version = "2.4.2"

        private const val roomRuntime = "androidx.room:room-runtime:$version"
        private const val roomKtx = "androidx.room:room-ktx:$version"
        private const val roomCompiler = "androidx.room:room-compiler:$version"

        override fun applyDependencies(scope: DependencyHandlerScope) {
            scope {
                implementation(roomRuntime)
                implementation(roomKtx)
                ksp(roomCompiler)
            }
        }
    }

    object Compose : Dependencies() {
        private const val version = "1.3.1"
        const val compilerVersion = "1.3.2"

        private const val activity = "androidx.activity:activity-compose:1.4.0"
        private const val animations = "androidx.compose.animation:animation:$version"
        private const val foundation = "androidx.compose.foundation:foundation:$version"
        private const val runtime = "androidx.compose.runtime:runtime:$version"
        private const val material = "androidx.compose.material:material:$version"
        private const val material3 = "androidx.compose.material3:material3:1.0.1"

        override fun applyDependencies(scope: DependencyHandlerScope) {
            scope {
                implementation(activity)
                implementation(animations)
                implementation(foundation)
                implementation(runtime)
                implementation(material)
                implementation(material3)
            }
        }
    }

    object Accompanist : Dependencies() {
        private const val version = "0.27.1"

        private const val swiperefresh = "com.google.accompanist:accompanist-swiperefresh:$version"
        private const val placeholderMaterial = "com.google.accompanist:accompanist-placeholder-material:$version"
        private const val flowlayout = "com.google.accompanist:accompanist-flowlayout:$version"
        private const val systemuicontroller = "com.google.accompanist:accompanist-systemuicontroller:$version"

        override fun applyDependencies(scope: DependencyHandlerScope) {
            scope {
                implementation(swiperefresh)
                implementation(placeholderMaterial)
                implementation(flowlayout)
                implementation(systemuicontroller)
            }
        }
    }

    object Koin : Dependencies() {
        private const val version = "3.3.0"

        private const val koin = "io.insert-koin:koin-android:$version"
        private const val koinCompose = "io.insert-koin:koin-androidx-compose:$version"

        override fun applyDependencies(scope: DependencyHandlerScope) {
            scope {
                implementation(koin)
                implementation(koinCompose)
            }
        }
    }

    abstract fun applyDependencies(scope: DependencyHandlerScope)

    protected fun DependencyHandlerScope.implementation(dependencyNotation: String) {
        "implementation"(dependencyNotation)
    }

    protected fun DependencyHandlerScope.ksp(dependencyNotation: String) {
        "ksp"(dependencyNotation)
    }
}
