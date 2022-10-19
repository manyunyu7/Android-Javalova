package com.feylabs.core.di

import com.feylabs.core.network.service.JavalovaApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun providesJavalovaApi(retrofit: Retrofit): JavalovaApi {
        return retrofit.create(JavalovaApi::class.java)
    }
    single { providesJavalovaApi(get()) }

}