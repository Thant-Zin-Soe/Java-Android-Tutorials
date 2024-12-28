plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.myphotoalbum"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myphotoalbum"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    val lifecycle_version = "2.8.7"
    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version")
    // LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata:$lifecycle_version")

    // annotation pocesspor
    implementation ("androidx.lifecycle:lifecycle-common-java8:$lifecycle_version")
}