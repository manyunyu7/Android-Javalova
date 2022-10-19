package com.feylabs.core.di

import android.content.Context
import com.feylabs.core.data.local.JavalovaDAO
import com.feylabs.core.data.local.category.DrinkAlcoholicCategoryDAO
import com.feylabs.core.data.local.category.DrinkGeneralCategoryDAO
import com.feylabs.core.data.local.category.DrinkGlassCategoryDAO
import com.feylabs.core.data.local.category.DrinkIngredientCategoryDAO
import com.feylabs.core.data.remote.CocktailCategoryDataSource
import com.feylabs.core.data.remote.JavalovaDataSource
import com.feylabs.core.data.repository.CocktailCategoryRepository
import com.feylabs.core.data.repository.CocktailCategoryRepositoryImpl
import com.feylabs.core.data.repository.JavalovaRepository
import com.feylabs.core.data.repository.JavalovaRepositoryImpl
import com.feylabs.core.network.service.CocktailCategoryApi
import com.feylabs.core.network.service.JavalovaApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {

    fun provideJavalovaDataSource(api: JavalovaApi): JavalovaDataSource {
        return JavalovaDataSource(api = api)
    }

    fun provideCocktailCategoryDataSource(api: CocktailCategoryApi): CocktailCategoryDataSource {
        return CocktailCategoryDataSource(api = api)
    }

    fun provideJavalovaRepository(
        ds: JavalovaDataSource,
        context: Context,
        dao: JavalovaDAO
    ): JavalovaRepository {
        return JavalovaRepositoryImpl(ds, context, dao)
    }

    fun provideCocktailRepository(
        ds: CocktailCategoryDataSource,
        context: Context,
        alcoholicDAO: DrinkAlcoholicCategoryDAO,
        glassDAO: DrinkGlassCategoryDAO,
        ingredientDAO: DrinkIngredientCategoryDAO,
        generalDAO: DrinkGeneralCategoryDAO,
    ): CocktailCategoryRepository {
        return CocktailCategoryRepositoryImpl(ds, context, alcoholicDAO,glassDAO,ingredientDAO,generalDAO)
    }

    single { provideJavalovaDataSource(get(named("exampleApi"))) }
    single { provideJavalovaRepository(get(), androidContext(), get()) }


    single { provideCocktailCategoryDataSource(get(named("cocktailCategoryApi"))) }
    single { provideCocktailRepository(get(), androidContext(), get(),get(),get(),get()) }
}