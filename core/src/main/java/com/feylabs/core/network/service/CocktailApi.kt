package com.feylabs.core.network.service

import com.feylabs.core.domain.QuoteApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface CocktailApi {

    @GET("quotes")
    suspend fun getAllJavalova(): Response<List<QuoteApiResponse.QuoteApiResponseItem>>
}