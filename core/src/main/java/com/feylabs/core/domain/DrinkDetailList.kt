package com.feylabs.core.domain


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class DrinkDetailList(
    @SerializedName("drinks")
    var drinks: List<DrinkDetail> = listOf()
) {
}

@Keep
data class DrinkDetail(
    @SerializedName("dateModified")
    var dateModified: String? = "",
    @SerializedName("idDrink")
    var idDrink: String = "",
    @SerializedName("strAlcoholic")
    var strAlcoholic: String = "",
    @SerializedName("strCategory")
    var strCategory: String = "",
    @SerializedName("strCreativeCommonsConfirmed")
    var strCreativeCommonsConfirmed: String = "",
    @SerializedName("strDrink")
    var strDrink: String = "",
    @SerializedName("strDrinkAlternate")
    var strDrinkAlternate: Any? = Any(),
    @SerializedName("strDrinkThumb")
    var strDrinkThumb: String = "",
    @SerializedName("strGlass")
    var strGlass: String = "",
    @SerializedName("strIBA")
    var strIBA: String? = "",
    @SerializedName("strImageAttribution")
    var strImageAttribution: String? = "",
    @SerializedName("strImageSource")
    var strImageSource: String? = "",
    @SerializedName("strIngredient1")
    var strIngredient1: String = "",
    @SerializedName("strIngredient10")
    var strIngredient10: Any? = Any(),
    @SerializedName("strIngredient11")
    var strIngredient11: Any? = Any(),
    @SerializedName("strIngredient12")
    var strIngredient12: Any? = Any(),
    @SerializedName("strIngredient13")
    var strIngredient13: Any? = Any(),
    @SerializedName("strIngredient14")
    var strIngredient14: Any? = Any(),
    @SerializedName("strIngredient15")
    var strIngredient15: Any? = Any(),
    @SerializedName("strIngredient2")
    var strIngredient2: String = "",
    @SerializedName("strIngredient3")
    var strIngredient3: String = "",
    @SerializedName("strIngredient4")
    var strIngredient4: String? = "",
    @SerializedName("strIngredient5")
    var strIngredient5: String? = "",
    @SerializedName("strIngredient6")
    var strIngredient6: String? = "",
    @SerializedName("strIngredient7")
    var strIngredient7: String? = "",
    @SerializedName("strIngredient8")
    var strIngredient8: Any? = Any(),
    @SerializedName("strIngredient9")
    var strIngredient9: Any? = Any(),
    @SerializedName("strInstructions")
    var strInstructions: String = "",
    @SerializedName("strInstructionsDE")
    var strInstructionsDE: String? = "",
    @SerializedName("strInstructionsES")
    var strInstructionsES: Any? = Any(),
    @SerializedName("strInstructionsFR")
    var strInstructionsFR: Any? = Any(),
    @SerializedName("strInstructionsIT")
    var strInstructionsIT: String = "",
    @SerializedName("strInstructionsZH-HANS")
    var strInstructionsZHHANS: Any? = Any(),
    @SerializedName("strInstructionsZH-HANT")
    var strInstructionsZHHANT: Any? = Any(),
    @SerializedName("strMeasure1")
    var strMeasure1: String = "",
    @SerializedName("strMeasure10")
    var strMeasure10: Any? = Any(),
    @SerializedName("strMeasure11")
    var strMeasure11: Any? = Any(),
    @SerializedName("strMeasure12")
    var strMeasure12: Any? = Any(),
    @SerializedName("strMeasure13")
    var strMeasure13: Any? = Any(),
    @SerializedName("strMeasure14")
    var strMeasure14: Any? = Any(),
    @SerializedName("strMeasure15")
    var strMeasure15: Any? = Any(),
    @SerializedName("strMeasure2")
    var strMeasure2: String = "",
    @SerializedName("strMeasure3")
    var strMeasure3: String = "",
    @SerializedName("strMeasure4")
    var strMeasure4: String? = "",
    @SerializedName("strMeasure5")
    var strMeasure5: String? = "",
    @SerializedName("strMeasure6")
    var strMeasure6: String? = "",
    @SerializedName("strMeasure7")
    var strMeasure7: String? = "",
    @SerializedName("strMeasure8")
    var strMeasure8: Any? = Any(),
    @SerializedName("strMeasure9")
    var strMeasure9: Any? = Any(),
    @SerializedName("strTags")
    var strTags: String? = "",
    @SerializedName("strVideo")
    var strVideo: Any? = Any()
)