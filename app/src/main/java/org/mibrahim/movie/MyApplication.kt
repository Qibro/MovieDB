package org.mibrahim.movie

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.mibrahim.movie.core.di.databaseModule
import org.mibrahim.movie.core.di.networkModule
import org.mibrahim.movie.core.di.repositoryModule
import org.mibrahim.movie.di.useCaseModule
import org.mibrahim.movie.di.viewModelModule

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}