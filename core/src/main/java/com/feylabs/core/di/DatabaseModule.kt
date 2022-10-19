package com.feylabs.core.di

import android.app.Application
import androidx.room.Room
import com.feylabs.core.data.local.JavalovaDAO
import com.feylabs.core.data.local.JavalovaDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideJavalovaDatabase(application: Application): JavalovaDatabase {
       return Room.databaseBuilder(application, JavalovaDatabase::class.java, "countries")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideJavalovaDao(database: JavalovaDatabase): JavalovaDAO {
        return  database.javalovaDao()
    }

    single { provideJavalovaDatabase(androidApplication()) }
    single { provideJavalovaDao(get()) }


}
