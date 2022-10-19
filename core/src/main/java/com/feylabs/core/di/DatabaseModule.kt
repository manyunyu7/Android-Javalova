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
            .allowMainThreadQueries()
            .build()
    }

    fun provideJavalovaDao(database: JavalovaDatabase): JavalovaDAO {
        return database.javalovaDao()
    }

    fun provideCategoryGlassDao(database: JavalovaDatabase) = database.categoryGlassDao()
    fun provideCategoryIngredientDao(database: JavalovaDatabase) = database.categoryIngredientDao()
    fun provideCategoryGeneralDao(database: JavalovaDatabase) = database.categoryGeneralDao()
    fun provideCategoryAlcoholicDao(database: JavalovaDatabase) = database.categoryAlcoholicDao()

    single { provideJavalovaDatabase(androidApplication()) }
    single { provideJavalovaDao(get()) }

    single { provideCategoryAlcoholicDao(get()) }
    single { provideCategoryGeneralDao(get()) }
    single { provideCategoryIngredientDao(get()) }
    single { provideCategoryGlassDao(get()) }


}
