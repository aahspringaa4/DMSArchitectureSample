plugins {
    id ("com.android.library")
    id ("kotlin-android")
    kotlin("kapt")
}

android {
    compileSdk = Project.compileSdk

    defaultConfig {
        minSdk = Project.minSdk
        targetSdk = Project.targetSdk
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled  = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = Project.javaVersion
        targetCompatibility = Project.javaVersion
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Dependency.Coroutine.core)
    implementation(Dependency.LocalStorage.room)
    kapt(Dependency.LocalStorage.roomCompiler)

    implementation(Dependency.DI.inject)
}