// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(DependencyInjection.hiltGradle)
    }
}

plugins {
    id("com.android.library") version ("7.1.2") apply false
    id("org.jetbrains.kotlin.android") version ("1.5.30") apply false
    id("org.jetbrains.kotlin.jvm") version ("1.5.30") apply false
}
