plugins {
    id("com.android.library")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Apps.compileSdk
}

dependencies {
    implementation(Dep.AndroidX.core)
    implementation(Dep.AndroidX.appcompat)
    implementation(Dep.AndroidX.material)
    implementation(Libs.gson)
    implementation(Libs.gsonConverter)
    implementation(Libs.retrofit2)
    implementation(Libs.hilt)
    implementation(Libs.okhttp3)
    androidTestImplementation(Dep.Test.ext)
    androidTestImplementation(Dep.Test.espresso)
    testImplementation(Dep.Test.jUnit)
    kapt(Libs.hiltCompiler)
}