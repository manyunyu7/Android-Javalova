package com.feylabs.core.data.remote

import com.feylabs.core.domain.*
import com.feylabs.core.domain.DrinkAlcoholicCategory.DrinkAlcoholicCategoryItem
import com.feylabs.core.domain.DrinkGlassCategory.DrinkGlassCategoryItem
import com.feylabs.core.domain.DrinkIngredientCategory.DrinkIngredientCategoryItem
import com.feylabs.core.network.service.CocktailCategoryApi
import com.feylabs.core.network.service.CocktailMainApi
import com.feylabs.core.util.AppResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow

class CocktailCategoryDataSource(
    private val api: CocktailCategoryApi,
    private val drinkApi: CocktailMainApi,
) {

    suspend fun getGeneralCategory(): Flow<AppResult<List<DrinkGeneralCategory.DrinkGeneralCategoryItem?>>> {
        return flow {
            emit(AppResult.Loading())
            val res = api.getGeneralCategory()
            if (res.isSuccessful) {
                var data = res.body()
                emit(AppResult.Success(res.body()?.drinks))
            } else {
                emit(AppResult.Error(res.errorBody().toString()))
            }
        }
    }

    suspend fun getGlassCategory(): Flow<AppResult<List<DrinkGlassCategoryItem?>>> {
        return flow {
            emit(AppResult.Loading())
            val res = api.getGlassCategory()
            if (res.isSuccessful) {
                emit(AppResult.Success(res.body()?.drinks))
            } else {
                emit(AppResult.Error(res.errorBody().toString()))
            }
        }
    }

    suspend fun getIngredientCategory(): Flow<AppResult<List<DrinkIngredientCategoryItem?>>> {
        return flow {
            emit(AppResult.Loading())
            val res = api.getIngredientCategory()
            if (res.isSuccessful) {
                emit(AppResult.Success(res.body()?.drinks))
            } else {
                emit(AppResult.Error(res.errorBody().toString()))
            }
        }
    }

    suspend fun getAlcoholicCategory(): Flow<AppResult<List<DrinkAlcoholicCategoryItem?>>> {
        return flow {
            emit(AppResult.Loading())
            val res = api.getAlcoholicCategory()
            if (res.isSuccessful) {
                emit(AppResult.Success(res.body()?.drinks))
            } else {
                emit(AppResult.Error(res.errorBody().toString()))
            }
        }
    }

    suspend fun getListDrink(
        category: String? = null,
        ingredient: String? = null,
        glass: String? = null,
        alcoholic: String? = null
    ): Flow<AppResult<List<DrinkGeneral?>>> {
        return flow {
            emit(AppResult.Loading())
            val res =
                drinkApi.getListDrink(
                    generalCategory = category,
                    ingredientCategory = ingredient,
                    glassCategory = glass,
                    alcoholicCategory = alcoholic
                )
            if (res.isSuccessful) {
                emit(AppResult.Success(res.body()?.drinks))
            } else {
                emit(AppResult.Error(res.errorBody().toString()))
            }
        }
    }

}

