package com.feylabs.core.network.service

import com.feylabs.core.domain.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailMainApi {
//    https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Shake&i=Rum&g=Balloon%20Glass&a=Optional%20alcohol

    @GET("filter.php")
    suspend fun getListDrink(
        @Query("c") generalCategory: String? = null,
        @Query("i") ingredientCategory: String? = null,
        @Query("a") alcoholicCategory: String? = null,
        @Query("g") glassCategory: String? = null,
    ): Response<DrinkGeneralResponse>

}