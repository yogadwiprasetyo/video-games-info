package technical.test.yprsty.di

import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import technical.test.yprsty.data.GameRepository
import technical.test.yprsty.data.paging.GamePagingSource
import technical.test.yprsty.data.source.locale.LocaleDataSource
import technical.test.yprsty.data.source.locale.room.GameDatabase
import technical.test.yprsty.data.source.remote.RemoteDataSource
import technical.test.yprsty.data.source.remote.network.ApiService
import technical.test.yprsty.domain.repository.IGameRepository
import technical.test.yprsty.domain.usecase.GameInteractor
import technical.test.yprsty.domain.usecase.GameUseCase
import java.util.concurrent.TimeUnit

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
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val dataLayerModule = module {
    single {
        LocaleDataSource(get())
    }
    single {
        RemoteDataSource(get())
    }
    factory {
        GamePagingSource(get())
    }
    single<IGameRepository> {
        GameRepository(get(), get(), get())
    }
}

val domainLayerModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
}

val presentationModule = module {

}