package org.mibrahim.movie.core.di

import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.mibrahim.movie.core.data.MovieRepository
import org.mibrahim.movie.core.data.source.local.LocalDataSource
import org.mibrahim.movie.core.data.source.local.room.MoviewDatabase
import org.mibrahim.movie.core.data.source.remote.RemoteDataSource
import org.mibrahim.movie.core.data.source.remote.network.ApiService
import org.mibrahim.movie.core.domain.repository.IMovieRepository
import org.mibrahim.movie.core.utils.AppExecutors
import org.mibrahim.movie.core.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

    val databaseModule = module {
        factory { get<MoviewDatabase>().movieDao() }
        single {
            Room.databaseBuilder(
                androidContext(),
                MoviewDatabase::class.java, "Movie.db"
            ).fallbackToDestructiveMigration().build()
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
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
            retrofit.create(ApiService::class.java)
        }
    }

    val repositoryModule = module {
        single { LocalDataSource(get()) }
        single { RemoteDataSource(get()) }
        factory { AppExecutors() }
        single<IMovieRepository> {
            MovieRepository(
                get(),
                get(),
                get()
            )
        }
    }