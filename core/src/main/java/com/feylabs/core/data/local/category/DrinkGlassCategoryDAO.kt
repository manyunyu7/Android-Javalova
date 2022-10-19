package com.feylabs.core.data.local.category

import androidx.room.*
import com.feylabs.core.domain.DrinkGeneralCategory
import com.feylabs.core.domain.DrinkGlassCategory
import com.feylabs.core.domain.DrinkGlassCategory.DrinkGlassCategoryItem
import com.feylabs.core.domain.QuoteApiResponse  as Md

@Dao
interface DrinkGlassCategoryDAO {


    @Query("SELECT * FROM drinkglasscategoryitem")
    fun getAll(): List<DrinkGlassCategoryItem>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(quotes: List<DrinkGlassCategoryItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(quotes: DrinkGlassCategoryItem)

    @Delete
    fun delete(quotes: DrinkGlassCategoryItem)

    @Query("DELETE FROM drinkglasscategoryitem where strGlass <> 0")
    fun deleteAll()
}