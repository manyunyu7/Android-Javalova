package com.feylabs.core.data.local.category

import androidx.room.*
import com.feylabs.core.domain.DrinkGlassCategory
import com.feylabs.core.domain.DrinkIngredientCategory
import com.feylabs.core.domain.DrinkIngredientCategory.DrinkIngredientCategoryItem
import com.feylabs.core.domain.QuoteApiResponse  as Md

@Dao
interface DrinkIngredientCategoryDAO {

    @Query("SELECT * FROM drinkingredientcategoryitem")
    fun getAll(): List<DrinkIngredientCategoryItem>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(quotes: List<DrinkIngredientCategoryItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(quotes: DrinkIngredientCategoryItem)

    @Delete
    fun delete(quotes: DrinkIngredientCategoryItem)

    @Query("DELETE FROM drinkingredientcategoryitem where strIngredient1 <> 0")
    fun deleteAll()
}