package com.feylabs.core.domain


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
data class DrinkGeneralResponse(
    @SerializedName("drinks")
    var drinks: List<DrinkGeneral> = listOf()
) {

}
@Entity
@Keep
data class DrinkGeneral(
    @SerializedName("idDrink")
    @PrimaryKey
    var idDrink: String = "",
    @SerializedName("strDrink")
    var strDrink: String = "",
    @SerializedName("strDrinkThumb")
    var strDrinkThumb: String = ""
)