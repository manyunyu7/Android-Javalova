package com.feylabs.core.data.remote

import com.feylabs.core.domain.*
import com.feylabs.core.domain.DrinkAlcoholicCategory.DrinkAlcoholicCategoryItem
import com.feylabs.core.domain.DrinkGlassCategory.DrinkGlassCategoryItem
import com.feylabs.core.domain.DrinkIngredientCategory.DrinkIngredientCategoryItem
import com.feylabs.core.network.service.CocktailCategoryApi
import com.feylabs.core.util.AppResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow

class CocktailCategoryDataSource(
    private val api: CocktailCategoryApi,
) {

    suspend fun getGeneralCategory(): Flow<AppResult<List<DrinkGeneralCategory.DrinkGeneralCategoryItem?>>> {
        return flow {
            emit(AppResult.Loading())
            val res = api.getGeneralCategory()
            if (res.isSuccessful){
                emit(AppResult.Success(res.body()))
            }else{
                emit(AppResult.Error(res.errorBody().toString()))
            }
        }
    }

    suspend fun getGlassCategory(): Flow<AppResult<List<DrinkGlassCategoryItem?>>> {
        return flow {
            emit(AppResult.Loading())
            val res = api.getGlassCategory()
            if (res.isSuccessful){
                emit(AppResult.Success(res.body()))
            }else{
                emit(AppResult.Error(res.errorBody().toString()))
            }
        }
    }

    suspend fun getIngredientCategory(): Flow<AppResult<List<DrinkIngredientCategoryItem?>>> {
        return flow {
            emit(AppResult.Loading())
            val res = api.getIngredientCategory()
            if (res.isSuccessful){
                emit(AppResult.Success(res.body()))
            }else{
                emit(AppResult.Error(res.errorBody().toString()))
            }
        }
    }

    suspend fun getAlcoholicCategory(): Flow<AppResult<List<DrinkAlcoholicCategoryItem?>>> {
        return flow {
            emit(AppResult.Loading())
            val res = api.getAlcoholicCategory()
            if (res.isSuccessful){
                emit(AppResult.Success(res.body()))
            }else{
                emit(AppResult.Error(res.errorBody().toString()))
            }
        }
    }

}

