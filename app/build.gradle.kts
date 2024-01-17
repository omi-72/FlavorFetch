plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-parcelize")
    id ("kotlin-kapt")
    id ("com.google.devtools.ksp")
}

android {
    namespace = "com.example.foodapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.foodapp"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    val nav_version = "2.7.5"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //rofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.google.code.gson:gson:2.9.0")

    // Lifecycle components mvvm
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-common-java8:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    //noinspection LifecycleAnnotationProcessorWithJava8
    kapt ("androidx.lifecycle:lifecycle-compiler:2.6.1")
    implementation ("androidx.activity:activity-ktx:1.7.2")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation ("androidx.navigation:navigation-ui-ktx:2.5.3")

    //live data
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    //Room Database
//    implementation ("androidx.room:room-runtime:2.5.1")
//    kapt ("androidx.room:room-compiler:2.5.1")
//    implementation ("androidx.room:room-ktx:2.5.1")
//    androidTestImplementation ("androidx.room:room-testing:2.5.1")

    // Room
    //def room_version  ("2.5.2")
    implementation ("androidx.room:room-runtime:2.5.2")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation ("androidx.room:room-ktx:2.5.2")

    // To use Kotlin annotation processing tool (kapt)
//    kapt "androidx.room:room-compiler:$room_version"

    // To use Kotlin Symbol Processing (KSP)
    ksp ("androidx.room:room-compiler:2.5.0")

    implementation ("com.github.bumptech.glide:glide:4.15.1")

    //gif
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.17")


    // Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")



}