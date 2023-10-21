package dependency

import versions.Versions

object SharedDependency {
    // Default
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val material = "com.google.android.material:material:${Versions.androidMaterialVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"

    // Paging3
    const val paging3 = "androidx.paging:paging-runtime-ktx:${Versions.pagingVersion}"
    // Koin (Dependency Injection)
    const val koin = "io.insert-koin:koin-android:${Versions.koinAndroidVersion}"
    // Glide (Image)
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    // RxBinding (Reactive)
    const val rxBinding = "com.jakewharton.rxbinding4:rxbinding:${Versions.rxBindingVersion}"
    // Timber
    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"

    // Testing
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunitVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
    
    val allDependency = arrayListOf<String>().apply {
        add(coreKtx)
        add(appCompat)
        add(material)
        add(constraintLayout)
        add(paging3)
        add(koin)
        add(glide)
        add(rxBinding)
        add(timber)
        add(junit)
        add(extJunit)
        add(espressoCore)
    }
}