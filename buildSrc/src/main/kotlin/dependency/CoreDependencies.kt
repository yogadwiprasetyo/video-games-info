package dependency

import versions.Versions

object CoreDependencies {
    // Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"

    // Third-party Libraries
    // Network (Retrofit, GSON, OkHttp3)
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val okhttpBoom = "com.squareup.okhttp3:okhttp-bom:${Versions.okhttpVersion}"
    const val okhttp = "com.squareup.okhttp3:okhttp"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor"

    val allDependency = arrayListOf<String>().apply {
        add(roomRuntime)
        add(roomKtx)
        add(retrofit)
        add(retrofitConverterGson)
        add(okhttpBoom)
        add(okhttp)
        add(okhttpLogging)
    }
}