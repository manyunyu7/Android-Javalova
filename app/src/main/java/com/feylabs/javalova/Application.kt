package com.feylabs.javalova

import android.app.Application
import com.feylabs.core.di.databaseModule
import com.feylabs.core.di.networkModule
import com.feylabs.core.di.repositoryModule
import com.feylabs.javalova.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

open class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger(Level.DEBUG)
            androidLogger(Level.ERROR)
            androidContext(this@Application)
            modules(listOf(
                databaseModule,
                networkModule,
                repositoryModule,
                viewModelModule
            ))
        }
    }

}