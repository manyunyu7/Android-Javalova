package com.feylabs.core.domain


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
data class DrinkGlassCategory(
    @SerializedName("drinks")
    var drinks: List<DrinkGlassCategoryItem> = listOf()
) {
    @Keep
    @Entity
    data class DrinkGlassCategoryItem(
        @PrimaryKey
        @SerializedName("strGlass")
        var strGlass: String = ""
    )
}