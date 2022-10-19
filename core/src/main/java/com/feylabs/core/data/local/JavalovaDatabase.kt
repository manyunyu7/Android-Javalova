package com.feylabs.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.feylabs.core.data.local.category.DrinkAlcoholicCategoryDAO
import com.feylabs.core.data.local.category.DrinkGeneralCategoryDAO
import com.feylabs.core.data.local.category.DrinkGlassCategoryDAO
import com.feylabs.core.data.local.category.DrinkIngredientCategoryDAO
import com.feylabs.core.domain.*
import com.feylabs.core.domain.DrinkAlcoholicCategory.DrinkAlcoholicCategoryItem
import com.feylabs.core.domain.DrinkGeneralCategory.DrinkGeneralCategoryItem
import com.feylabs.core.domain.DrinkGlassCategory.DrinkGlassCategoryItem
import com.feylabs.core.domain.DrinkIngredientCategory.DrinkIngredientCategoryItem
import com.feylabs.core.domain.QuoteApiResponse.QuoteApiResponseItem

@Database(entities = [
    QuoteApiResponseItem::class,
    DrinkGeneralCategoryItem::class,
    DrinkGlassCategoryItem::class,
    DrinkIngredientCategoryItem::class,
    DrinkGeneral::class,
    DrinkAlcoholicCategoryItem::class], version = 168)
abstract class JavalovaDatabase : RoomDatabase() {
    abstract fun javalovaDao(): JavalovaDAO

    abstract fun categoryGlassDao(): DrinkGlassCategoryDAO
    abstract fun categoryGeneralDao(): DrinkGeneralCategoryDAO
    abstract fun categoryIngredientDao(): DrinkIngredientCategoryDAO
    abstract fun categoryAlcoholicDao(): DrinkAlcoholicCategoryDAO

    abstract fun drinkListDao(): DrinkListDAO
}