package com.feylabs.core.domain


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
data class DrinkAlcoholicCategory(
    @SerializedName("drinks")
    var drinks: List<DrinkAlcoholicCategoryItem> = listOf()
) {
    @Keep
    @Entity()
    data class DrinkAlcoholicCategoryItem(
        @PrimaryKey
        @SerializedName("strAlcoholic")
        var strAlcoholic: String = ""
    )
}