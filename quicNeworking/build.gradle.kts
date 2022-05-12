import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 21
        targetSdk = 31

        val NEWS_API_KEY: String = gradleLocalProperties(rootDir).getProperty("NEWS_API_KEY")
        val WEATHER_API_KEY: String = gradleLocalProperties(rootDir).getProperty("WEATHER_API_KEY")
        val FINANCE_API_KEY: String = gradleLocalProperties(rootDir).getProperty("FINANCE_API_KEY")

        buildConfigField("String", "NEWS_API_KEY", NEWS_API_KEY)
        buildConfigField("String", "WEATHER_API_KEY", WEATHER_API_KEY)
        buildConfigField("String", "FINANCE_API_KEY", FINANCE_API_KEY)
    }

    buildTypes {
        getByName("release") {
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
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation(Kotlin.kotlinCoroutines)

    // network
    implementation(Network.retrofit)
    implementation(Network.gsonConverter)
    implementation(Network.gson)

    // DI
    implementation(DependencyInjection.hiltAndroid)
    kapt(DependencyInjection.hiltCompiler)

    //Testing
    testImplementation(Testing.jUnit)
    testImplementation(Testing.mockito)
}

kapt {
    correctErrorTypes = true
}