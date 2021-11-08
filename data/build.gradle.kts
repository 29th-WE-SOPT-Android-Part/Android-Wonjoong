plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")
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
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    androidTestImplementation(Dep.Test.ext)
    androidTestImplementation(Dep.Test.espresso)
    testImplementation(Dep.Test.jUnit)
}