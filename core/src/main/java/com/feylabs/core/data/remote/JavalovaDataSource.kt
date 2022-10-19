package com.feylabs.core.data.remote

import com.feylabs.core.domain.QuoteApiResponse
import com.feylabs.core.network.service.JavalovaApi
import com.feylabs.core.util.AppResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow

class JavalovaDataSource(
    private val api: JavalovaApi,
) {

    suspend fun getJavalovaData(): Flow<AppResult<List<QuoteApiResponse.QuoteApiResponseItem?>>> {
        return flow {
            emit(AppResult.Loading())
            val res = api.getAllJavalova()
            if (res.isSuccessful){
                emit(AppResult.Success(res.body()))
            }else{
                emit(AppResult.Error(res.errorBody().toString()))
            }
        }
    }
}

