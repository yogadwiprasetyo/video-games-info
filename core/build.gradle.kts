import projectRoot.AppConfig
import dependency.SharedDependency
import dependency.CoreDependencies

plugins {
    id(projectRoot.BuildPlugins.androidLibrary)
    id(projectRoot.BuildPlugins.kotlinAndroid)
    id(projectRoot.BuildPlugins.kotlinKsp)
}

android {
    namespace = "technical.test.core"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField(AppConfig.fieldTypeApiKey, AppConfig.fieldNameApiKey, AppConfig.apiKey)
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
    CoreDependencies.allDependency.forEach { implementation(it) }
    ksp(CoreDependencies.roomCompiler)
}