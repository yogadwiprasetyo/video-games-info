import dependency.AppDependencies
import dependency.SharedDependency
import projectRoot.AppConfig

plugins {
    id(projectRoot.BuildPlugins.androidApplication)
    id(projectRoot.BuildPlugins.kotlinAndroid)
    id(projectRoot.BuildPlugins.navigationArgs)
}

android {
    namespace = AppConfig.nameSpace
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    SharedDependency.allDependency.forEach { implementation(it) }
    AppDependencies.allDependency.forEach { implementation(it) }
    implementation(project(":core"))
}