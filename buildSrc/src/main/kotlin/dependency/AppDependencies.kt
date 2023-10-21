package dependency

import versions.Versions.androidMaterialVersion
import versions.Versions.appCompatVersion
import versions.Versions.constraintLayoutVersion
import versions.Versions.coreKtxVersion
import versions.Versions.espressoCoreVersion
import versions.Versions.extJunitVersion
import versions.Versions.glideVersion
import versions.Versions.junitVersion
import versions.Versions.koinAndroidVersion
import versions.Versions.lifecycleVersion
import versions.Versions.navSafeArgsVersion
import versions.Versions.navVersion
import versions.Versions.okhttpVersion
import versions.Versions.pagingVersion
import versions.Versions.retrofitVersion
import versions.Versions.roomVersion
import versions.Versions.rxBindingVersion
import versions.Versions.timberVersion

object AppDependencies {
    // Default
    const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
    const val material = "com.google.android.material:material:$androidMaterialVersion"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"

    // Jetpack Libraries
    // Paging3
    const val paging3 = "androidx.paging:paging-runtime-ktx:$pagingVersion"
    // Navigation
    const val navFragment = "androidx.navigation:navigation-fragment-ktx:$navVersion"
    const val navUi = "androidx.navigation:navigation-ui-ktx:$navVersion"
    // Put on root project (classpath)
    const val navSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$navSafeArgsVersion"
    // Lifecycle (ViewModel & LiveData)
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    // Room
    const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
    const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    const val roomPaging = "androidx.room:room-paging:$roomVersion"

    // Third-party Libraries
    // Koin (Dependency Injection)
    const val koin = "io.insert-koin:koin-android:$koinAndroidVersion"
    // Glide (Image)
    const val glide = "com.github.bumptech.glide:glide:$glideVersion"
    // Network (Retrofit, GSON, OkHttp3)
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    const val okhttpBoom = "com.squareup.okhttp3:okhttp-bom:$okhttpVersion"
    const val okhttp = "com.squareup.okhttp3:okhttp"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor"
    // RxBinding (Reactive)
    const val rxBinding = "com.jakewharton.rxbinding4:rxbinding:$rxBindingVersion"
    // Timber
    const val timber = "com.jakewharton.timber:timber:$timberVersion"

    // Testing
    const val junit = "junit:junit:$junitVersion"
    const val extJunit = "androidx.test.ext:junit:$extJunitVersion"
    const val espressoCore = "androidx.test.espresso:espresso-core:$espressoCoreVersion"
}