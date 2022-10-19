package com.feylabs.core.di

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.feylabs.core.network.service.CocktailCategoryApi
import com.feylabs.core.network.service.CocktailMainApi
import com.feylabs.core.network.service.JavalovaApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig.DEBUG
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    val baseURL = "https://type.fit/api/"
    val cocktailBaseURL = "https://www.thecocktaildb.com/api/json/v1/1/"
    val connectTimeout : Long = 40// 20s
    val readTimeout : Long  = 40 // 20s

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(
                ChuckerInterceptor.Builder(androidContext())
                    .collector(ChuckerCollector(androidContext()))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(true)
                    .build()
            )
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }


    single<JavalovaApi>(named("exampleApi")) {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(JavalovaApi::class.java)
    }

    single<CocktailCategoryApi>(named("cocktailCategoryApi")) {
        val retrofit = Retrofit.Builder()
            .baseUrl(cocktailBaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(CocktailCategoryApi::class.java)
    }

    single<CocktailMainApi>(named("cocktailMainApi")) {
        val retrofit = Retrofit.Builder()
            .baseUrl(cocktailBaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(CocktailMainApi::class.java)
    }

}



