plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
}

android {
    namespace = "technical.test.yprsty"
    compileSdk = 34

    defaultConfig {
        applicationId = "technical.test.yprsty"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    val pagingVersion = "3.2.1"
    val koinAndroidVersion = "3.5.0"
    val lifecycleVersion = "2.6.2"
    val roomVersion = "2.6.0"
    val glideVersion = "4.16.0"
    val retrofitVersion = "2.9.0"
    val okhttpVersion = "4.11.0"
    val rxBindingVersion = "4.0.0"
    val navVersion = "2.7.4"
    val timberVersion = "5.0.1"

    // Default
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Jetpack Libraries
    // Paging3
    implementation("androidx.paging:paging-runtime-ktx:$pagingVersion")
    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    // Lifecycle (ViewModel & LiveData)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    // Room
    implementation("androidx.room:room-runtime:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.room:room-paging:$roomVersion")

    // Third-party Libraries
    // Koin (Dependency Injection)
    implementation("io.insert-koin:koin-android:$koinAndroidVersion")
    // Glide (Image)
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    // Network (Retrofit, GSON, OkHttp3)
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation(platform("com.squareup.okhttp3:okhttp-bom:$okhttpVersion"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")
    // RxBinding (Reactive)
    implementation("com.jakewharton.rxbinding4:rxbinding:$rxBindingVersion")
    // Timber
    implementation("com.jakewharton.timber:timber:$timberVersion")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}