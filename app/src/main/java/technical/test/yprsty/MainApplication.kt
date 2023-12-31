package technical.test.yprsty

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import technical.test.core.di.dataLayerModule
import technical.test.core.di.databaseModule
import technical.test.core.di.domainLayerModule
import technical.test.core.di.networkModule
import technical.test.yprsty.di.presentationModule
import timber.log.Timber

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            loadKoinModules(
                listOf(
                    databaseModule,
                    networkModule,
                    dataLayerModule,
                    domainLayerModule,
                    presentationModule
                )
            )
        }
    }
}