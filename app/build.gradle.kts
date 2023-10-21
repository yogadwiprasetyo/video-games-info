import dependency.AppDependencies
import projectRoot.AppConfig

plugins {
    id(projectRoot.BuildPlugins.androidApplication)
    id(projectRoot.BuildPlugins.kotlinAndroid)
    id(projectRoot.BuildPlugins.kotlinParcelize)
    id(projectRoot.BuildPlugins.kotlinKsp)
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
    implementation(AppDependencies.coreKtx)
    implementation(AppDependencies.appCompat)
    implementation(AppDependencies.material)
    implementation(AppDependencies.constraintLayout)

    // Jetpack Libs
    implementation(AppDependencies.paging3)
    implementation(AppDependencies.navFragment)
    implementation(AppDependencies.navUi)
    implementation(AppDependencies.lifecycleViewModel)
    implementation(AppDependencies.lifecycleLiveData)
    implementation(AppDependencies.roomRuntime)
    ksp(AppDependencies.roomCompiler)
    implementation(AppDependencies.roomKtx)
    implementation(AppDependencies.roomPaging)

    // Third-party Libraries
    implementation(AppDependencies.koin)
    implementation(AppDependencies.glide)
    implementation(AppDependencies.retrofit)
    implementation(AppDependencies.retrofitConverterGson)
    implementation(platform(AppDependencies.okhttpBoom))
    implementation(AppDependencies.okhttp)
    implementation(AppDependencies.okhttpLogging)
    implementation(AppDependencies.rxBinding)
    implementation(AppDependencies.timber)

    // Testing
    testImplementation(AppDependencies.junit)
    androidTestImplementation(AppDependencies.extJunit)
    androidTestImplementation(AppDependencies.espressoCore)
}