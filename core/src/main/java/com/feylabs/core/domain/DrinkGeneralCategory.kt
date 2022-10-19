package com.feylabs.core.domain


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
data class DrinkGeneralCategory(
    @SerializedName("drinks")
    var drinks: List<DrinkGeneralCategoryItem> = listOf()
) {
    @Keep
    @Entity
    data class DrinkGeneralCategoryItem(
        @PrimaryKey
        @SerializedName("strCategory")
        var strCategory: String = ""
    )
}