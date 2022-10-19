package com.feylabs.core.domain

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class QuoteApiResponse : ArrayList<QuoteApiResponse.QuoteApiResponseItem>(){
    @Keep
    @Entity()
    data class QuoteApiResponseItem(
        @SerializedName("author")
        var author: String? = "",
        @PrimaryKey
        @SerializedName("text")
        var text: String = ""
    ){
    }
}