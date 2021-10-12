object Apps {
    const val compileSdk = 31
    const val minSdk = 26
    const val targetSdk = 31
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val gradle = "4.2.0"
    const val kotlin = "1.5.21"
    const val appcompat = "1.3.1"
    const val junit = "4.13.2"
    const val activity = "1.3.1"
    const val fragment = "1.4.0-alpha10"
}

object Dep {
    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.3.1"
        const val core = "androidx.core:core-ktx:1.6.0"
        const val material = "com.google.android.material:material:1.4.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.1"
        const val activityKtx = "androidx.activity:activity-ktx:${Versions.activity}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    }

    object Test {
        const val jUnit = "junit:junit:${Versions.junit}"
        const val ext = "androidx.test.ext:junit:1.1.3"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    }
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
}

