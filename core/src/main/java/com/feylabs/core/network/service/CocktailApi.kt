package com.feylabs.core.network.service

import com.feylabs.core.domain.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApi {

    @GET("list.php?c=list")
    suspend fun getGeneralCategory(
    ): Response<List<DrinkGeneralCategory.DrinkGeneralCategoryItem>>

    @GET("list.php?g=list")
    suspend fun getGlassCategory(
    ): Response<List<DrinkGlassCategory.DrinkGlassCategoryItem>>

    @GET("list.php?i=list")
    suspend fun getIngredientCategory(
    ): Response<List<DrinkIngredientCategory.DrinkIngredientCategoryItem>>

    @GET("list.php?a=list")
    suspend fun getAlcoholicCategory(
    ): Response<List<DrinkAlcoholicCategory.DrinkAlcoholicCategoryItem>>

}