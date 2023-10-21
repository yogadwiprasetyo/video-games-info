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
    // Jetpack Libraries
    // Navigation
    const val navFragment = "androidx.navigation:navigation-fragment-ktx:$navVersion"
    const val navUi = "androidx.navigation:navigation-ui-ktx:$navVersion"
    // Put on root project (classpath)
    const val navSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$navSafeArgsVersion"
    // Lifecycle (ViewModel & LiveData)
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"

    val allDependency = arrayListOf<String>().apply {
        add(navFragment)
        add(navUi)
        add(lifecycleViewModel)
        add(lifecycleLiveData)
    }
}