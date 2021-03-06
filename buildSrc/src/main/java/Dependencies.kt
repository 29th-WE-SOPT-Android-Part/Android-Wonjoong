object Apps {
    const val compileSdk = 31
    const val minSdk = 26
    const val targetSdk = 31
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val gradle = "7.0.2"
    const val kotlin = "1.5.21"
    const val appcompat = "1.3.1"
    const val junit = "4.13.2"
    const val room = "2.3.0"
    const val activity = "1.3.1"
    const val fragment = "1.4.0-alpha10"
    const val navigationVersion = "2.4.0-alpha10"
    const val hilt = "2.38.1"
    const val hiltViewModel = "1.0.0-alpha03"
    const val lifeCycle = "2.4.0"
}

object Dep {
    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.3.1"
        const val core = "androidx.core:core-ktx:1.6.0"
        const val material = "com.google.android.material:material:1.4.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.1"
        const val activityKtx = "androidx.activity:activity-ktx:${Versions.activity}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
        const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
        const val navigationFragmentKtx =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
        const val navigationUiKtx =
            "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"
        const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
        const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}"
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
    const val glide = "com.github.bumptech.glide:glide:4.12.0"
    const val annotationGlide = "com.github.bumptech.glide:compiler:4.12.0"
    const val retrofit2 = "com.squareup.retrofit2:retrofit:2.9.0"
    const val gson = "com.google.code.gson:gson:2.8.5"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"
    const val hiltViewModelCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltViewModel}"
    const val okhttp3 = "com.squareup.okhttp3:okhttp:4.9.1"
}

