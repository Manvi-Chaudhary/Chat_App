plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
}

android {
    namespace = "com.example.chatapp"
    compileSdk = 33




    defaultConfig {
        applicationId = "com.example.chatapp"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    packagingOptions {
        exclude("META-INF/atomicfu.kotlin_module")
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("androidx.activity:activity-ktx:1.7.2")

    // Dependencies for working with Architecture components
    // You'll probably have to update the version numbers in build.gradle (Project)

    // Room components
    implementation("androidx.room:room-ktx:2.6.0-beta01")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.room:room-runtime:2.6.0-beta01")
    annotationProcessor("androidx.room:room-compiler:2.6.0-beta01")
    kapt ("androidx.room:room-compiler:2.6.0-beta01")

    androidTestImplementation ("androidx.room:room-testing:2.6.0-beta01")

    // Lifecycle components
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-common-java8:2.6.2")

    // Kotlin components
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.9.0")
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // UI
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("com.google.android.material:material:1.9.0")

    // Testing
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1", {
        exclude(group="com.android.support",module = "support-annotations")
    })
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
}