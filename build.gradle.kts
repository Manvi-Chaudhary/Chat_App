



// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    var kotlin_version="1.9.0"
    var activityVersion = "1.7.2"
    var appCompatVersion = "1.6.1"
    var constraintLayoutVersion = "2.1.4"
    var coreTestingVersion = "2.2.0"
    var coroutines = "1.7.3"
    var lifecycleVersion = "2.6.1"
    var materialVersion = "1.9.0"
    var roomVersion = "2.5.2"
    var junitVersion = "4.13.2"
    var espressoVersion = "3.5.1"
    var androidxJunitVersion = "1.1.5"
    repositories {
        google()
        mavenCentral()
    }


}
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}






