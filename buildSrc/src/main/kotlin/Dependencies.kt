import org.gradle.kotlin.dsl.DependencyHandlerScope

sealed class Dependencies {

    object AndroidxCore : Dependencies() {
        private const val version = "1.7.0"

        private const val core = "androidx.core:core:$version"
        private const val coreKtx = "androidx.core:core-ktx:$version"
        private const val coreSplashScreen = "androidx.core:core-splashscreen:1.0.0-beta01"

        override fun applyDependencies(scope: DependencyHandlerScope) {
            scope {
                implementation(core)
                implementation(coreKtx)
                implementation(coreSplashScreen)
            }
        }
    }

    object Compose : Dependencies() {
        const val version = "1.1.1"

        private const val activity = "androidx.activity:activity-compose:1.4.0"
        private const val animations = "androidx.compose.animation:animation:$version"
        private const val foundation = "androidx.compose.foundation:foundation:$version"
        private const val material = "androidx.compose.material:material:$version"
        private const val material3 = "androidx.compose.material3:material3:1.0.0-alpha07"

        override fun applyDependencies(scope: DependencyHandlerScope) {
            scope {
                implementation(activity)
                implementation(animations)
                implementation(foundation)
                implementation(material)
                implementation(material3)
            }
        }
    }

    object Koin : Dependencies() {
        private const val version = "3.1.5"

        private const val koin = "io.insert-koin:koin-android:$version"
        private const val koinCompose = "io.insert-koin:koin-android-compose:$version"

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
}
