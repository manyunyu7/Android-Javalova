package com.feylabs.core.di

import android.content.Context
import com.feylabs.core.data.local.JavalovaDAO
import com.feylabs.core.data.remote.JavalovaDataSource
import com.feylabs.core.data.repository.JavalovaRepository
import com.feylabs.core.data.repository.JavalovaRepositoryImpl
import com.feylabs.core.network.service.JavalovaApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    fun provideJavalovaDataSource(api: JavalovaApi): JavalovaDataSource {
        return JavalovaDataSource(api = api)
    }

    fun provideJavalovaRepository(
        ds: JavalovaDataSource,
        context: Context,
        dao: JavalovaDAO
    ): JavalovaRepository {
        return JavalovaRepositoryImpl(ds, context, dao)
    }

    single { provideJavalovaDataSource(get()) }
    single { provideJavalovaRepository(get(), androidContext(), get()) }
}