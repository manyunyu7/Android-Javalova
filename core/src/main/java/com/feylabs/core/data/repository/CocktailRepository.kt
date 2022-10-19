package com.feylabs.core.data.repository

import com.feylabs.core.domain.*
import com.feylabs.core.util.AppResult
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

interface CocktailRepository {
    suspend fun getGeneralCategory() : Flow<AppResult<List<DrinkGeneralCategory.DrinkGeneralCategoryItem>>?>
    suspend fun getGlassCategory() : Flow<AppResult<List<DrinkGlassCategory.DrinkGlassCategoryItem>>?>
    suspend fun getIngredientCategory() : Flow<AppResult<List<DrinkIngredientCategory.DrinkIngredientCategoryItem>>?>
    suspend fun getAlcoholic() : Flow<AppResult<List<DrinkAlcoholicCategory.DrinkAlcoholicCategoryItem>>?>
}
