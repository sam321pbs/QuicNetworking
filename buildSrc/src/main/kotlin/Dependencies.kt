object Network {
    object Version {
        const val retrofit = "2.9.0"
        const val gson = "2.9.0"
    }

    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
    const val gson = "com.google.code.gson:gson:${Version.gson}"
}

object Kotlin {
    object Version {
        const val kotlinCoroutines = "1.6.1"
    }
    const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.kotlinCoroutines}"
}

object DependencyInjection {
    object Version {
        const val hilt = "2.40.1"
    }

    const val hiltAndroid = "com.google.dagger:hilt-android:${Version.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Version.hilt}"
    const val hiltGradle = "com.google.dagger:hilt-android-gradle-plugin:${Version.hilt}"
}

object Testing {
    object Version {
        const val jUnit = "4.13.2"
        const val mockitoVersion = "3.+"
    }

    const val jUnit = "junit:junit:${Version.jUnit}"
    const val mockito = "org.mockito:mockito-core:${Version.mockitoVersion}"
}