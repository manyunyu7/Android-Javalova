package com.feylabs.core.data.local

import androidx.room.*
import com.feylabs.core.domain.QuoteApiResponse  as Md

@Dao
interface JavalovaDAO {

    @Query("SELECT * FROM quoteapiresponseitem order by author DESC")
    fun getAll(): List<Md.QuoteApiResponseItem>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(quotes: List<Md.QuoteApiResponseItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(quotes: Md.QuoteApiResponseItem)

    @Delete
    fun delete(quotes: Md.QuoteApiResponseItem)

    @Query("DELETE FROM QuoteApiResponseItem where author <> 0")
    fun deleteAll()
}