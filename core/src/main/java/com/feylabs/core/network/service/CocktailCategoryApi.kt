package com.feylabs.core.network.service

import com.feylabs.core.domain.*
import retrofit2.Response
import retrofit2.http.GET

interface CocktailCategoryApi {
//    https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Shake&i=Rum&g=Balloon%20Glass&a=Optional%20alcohol
    @GET("list.php?c=list")
    suspend fun getGeneralCategory(
    ): Response<DrinkGeneralCategory>

    @GET("list.php?g=list")
    suspend fun getGlassCategory(
    ): Response<DrinkGlassCategory>

    @GET("list.php?i=list")
    suspend fun getIngredientCategory(
    ): Response<DrinkIngredientCategory>

    @GET("list.php?a=list")
    suspend fun getAlcoholicCategory(
    ): Response<DrinkAlcoholicCategory>

}