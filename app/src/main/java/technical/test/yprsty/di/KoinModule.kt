package technical.test.yprsty.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import technical.test.yprsty.data.source.locale.LocaleDataSource
import technical.test.yprsty.data.source.locale.room.GameDatabase

val databaseModule = module {
    factory { get<GameDatabase>().gameDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java,
            "VideoGame.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {

}

val dataLayerModule = module {
    single {
        LocaleDataSource(get())
    }
}

val domainLayerModule = module {

}

val presentationModule = module {

}