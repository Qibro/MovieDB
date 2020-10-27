package org.mibrahim.movie.core.di

import androidx.room.Room
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
            val passPhrase : ByteArray = SQLiteDatabase.getBytes("movie".toCharArray())
            val factory = SupportFactory(passPhrase)
            Room.databaseBuilder(
                androidContext(),
                MoviewDatabase::class.java, "Movie.db"
            ).fallbackToDestructiveMigration()
                .openHelperFactory(factory)
                .build()
        }   
    }

    val networkModule = module {
        single {
            val hostname = "api.themoviedb.org"
            val certificatePinner = CertificatePinner.Builder()
                .add(hostname, "sha256/HkCBucsA3Tgiby96X7vjb/ojHaE1BrjvZ2+LRdJJd0E=")
                .add(hostname, "sha256/nKWcsYrc+y5I8vLf1VGByjbt+Hnasjl+9h8lNKJytoE=")
                .add(hostname, "sha256/r/mIkG3eEpVdm+u/ko/cwxzOMo1bk4TyHIlByibiA5E=")
                .build()
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .certificatePinner(certificatePinner)
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