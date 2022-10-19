package com.feylabs.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.feylabs.core.domain.QuoteApiResponse

@Database(entities = [QuoteApiResponse.QuoteApiResponseItem::class], version = 1)
abstract class JavalovaDatabase : RoomDatabase() {
    abstract fun javalovaDao(): JavalovaDAO
}