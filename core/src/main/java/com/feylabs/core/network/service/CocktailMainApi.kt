package com.feylabs.core.network.service

import com.feylabs.core.domain.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailMainApi {

    @GET("filter.php")
    suspend fun getListDrink(
        @Query("c") generalCategory: String? = null,
        @Query("i") ingredientCategory: String? = null,
        @Query("a") alcoholicCategory: String? = null,
        @Query("g") glassCategory: String? = null,
    ): Response<DrinkGeneralResponse>

    @GET("search.php?s=margarita")
    suspend fun searchCocktailByName(
        @Query("g") searchKeyword: String? = null,
    ): Response<DrinkDetailList>

    @GET("lookup.php?s=margarita")
    suspend fun lookupId(
        @Query("i") cocktailId: String? = null,
    ): Response<DrinkDetailList>


}