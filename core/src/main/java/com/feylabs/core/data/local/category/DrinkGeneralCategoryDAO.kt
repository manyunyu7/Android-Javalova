package com.feylabs.core.data.local.category

import androidx.room.*
import com.feylabs.core.domain.DrinkAlcoholicCategory
import com.feylabs.core.domain.DrinkGeneralCategory
import com.feylabs.core.domain.DrinkGeneralCategory.DrinkGeneralCategoryItem
import com.feylabs.core.domain.QuoteApiResponse  as Md

@Dao
interface DrinkGeneralCategoryDAO {

    @Query("SELECT * FROM drinkgeneralcategoryitem")
    fun getAll(): List<DrinkGeneralCategoryItem>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(quotes: List<DrinkGeneralCategoryItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(quotes: DrinkGeneralCategoryItem)

    @Delete
    fun delete(quotes: DrinkGeneralCategoryItem)

    @Query("DELETE FROM drinkgeneralcategoryitem where strCategory <> 0")
    fun deleteAll()
}