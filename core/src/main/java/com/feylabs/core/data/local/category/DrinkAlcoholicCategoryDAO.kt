package com.feylabs.core.data.local.category

import androidx.room.*
import com.feylabs.core.domain.DrinkAlcoholicCategory
import com.feylabs.core.domain.DrinkAlcoholicCategory.DrinkAlcoholicCategoryItem
import com.feylabs.core.domain.QuoteApiResponse  as Md

@Dao
interface DrinkAlcoholicCategoryDAO {

    @Query("SELECT * FROM drinkalcoholiccategoryitem")
    fun getAll(): List<DrinkAlcoholicCategoryItem>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(quotes: List<DrinkAlcoholicCategoryItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(quotes: DrinkAlcoholicCategoryItem)

    @Delete
    fun delete(quotes: DrinkAlcoholicCategoryItem)

    @Query("DELETE FROM drinkalcoholiccategoryitem where strAlcoholic <> 0")
    fun deleteAll()
}