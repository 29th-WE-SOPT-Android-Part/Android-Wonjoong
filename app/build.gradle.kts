plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    defaultConfig {
        compileSdk = Apps.compileSdk
        minSdk = Apps.minSdk
        targetSdk = Apps.targetSdk
        versionCode = Apps.versionCode
        versionName = Apps.versionName
        multiDexEnabled = true
        setProperty("archivesBaseName", "$applicationId-v$versionName($versionCode)")
        resourceConfigurations += "en"
        resourceConfigurations += "kr"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(Libs.appcompat)
    implementation(Libs.kotlin)
    implementation(Libs.glide)
    implementation(Libs.hilt)
    implementation(Libs.hiltViewModel)
    implementation(Libs.hiltViewModelCompiler)
    implementation(Dep.AndroidX.core)
    implementation(Dep.AndroidX.appcompat)
    implementation(Dep.AndroidX.material)
    implementation(Dep.AndroidX.constraintLayout)
    implementation(Dep.AndroidX.activityKtx)
    implementation(Dep.AndroidX.fragmentKtx)
    implementation(Dep.AndroidX.liveDataKtx)
    implementation(Dep.AndroidX.navigationFragmentKtx)
    implementation(Dep.AndroidX.navigationUiKtx)
    implementation(project(mapOf("path" to ":data")))
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    testImplementation(Dep.Test.jUnit)
    androidTestImplementation(Dep.Test.ext)
    androidTestImplementation(Dep.Test.espresso)
    annotationProcessor(Libs.annotationGlide)
    kapt(Libs.hiltCompiler)
}
