package com.feylabs.core.data.local

import androidx.room.*
import com.feylabs.core.domain.DrinkGeneral
import com.feylabs.core.domain.QuoteApiResponse  as Md

@Dao
interface DrinkListDAO {

    @Query("SELECT * FROM drinkgeneral WHERE idDrink > :lastValue ORDER BY idDrink LIMIT 5;")
    fun get(lastValue:String): List<DrinkGeneral>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(quotes: List<DrinkGeneral?>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(quotes:DrinkGeneral?)

    @Query("DELETE FROM drinkgeneral where idDrink <> -99")
    fun deleteAll()
}