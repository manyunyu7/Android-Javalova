package com.feylabs.core.domain


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
data class DrinkIngredientCategory(
    @SerializedName("drinks")
    var drinks: List<DrinkIngredientCategoryItem> = listOf()
) {
    @Keep
    @Entity
    data class DrinkIngredientCategoryItem(
        @SerializedName("strIngredient1")
        @PrimaryKey
        var strIngredient1: String = ""
    )
}