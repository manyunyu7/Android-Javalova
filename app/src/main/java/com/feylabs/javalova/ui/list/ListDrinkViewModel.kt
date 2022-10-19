package com.feylabs.javalova.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feylabs.core.data.repository.CocktailCategoryRepository
import com.feylabs.core.data.repository.JavalovaRepository
import com.feylabs.core.domain.*
import com.feylabs.core.domain.DrinkAlcoholicCategory.DrinkAlcoholicCategoryItem
import com.feylabs.core.domain.DrinkGeneralCategory.DrinkGeneralCategoryItem
import com.feylabs.core.domain.DrinkGlassCategory.DrinkGlassCategoryItem
import com.feylabs.core.domain.DrinkIngredientCategory.DrinkIngredientCategoryItem
import com.feylabs.core.util.AppResult
import kotlinx.coroutines.launch

class ListDrinkViewModel(
    private val repository: JavalovaRepository,
    private val categoryRepository: CocktailCategoryRepository
) : ViewModel() {


    private var _drinkListLiveData =
        MutableLiveData<AppResult<List<DrinkGeneral>>>()
    val drinkListLiveData get() = _drinkListLiveData

    fun getDrinkList(
        category: String?,
        glass: String?,
        ingredient: String?,
        alcoholic: String?,
        lastValue: String?,
    ) {
        _drinkListLiveData.postValue(AppResult.Loading())
        viewModelScope.launch {
            val result = categoryRepository.getListDrink(
                category = category,
                glass = glass,
                ingredient = ingredient,
                alcoholic = alcoholic,
                lastValue = lastValue
            )
            result.collect {
                _drinkListLiveData.postValue(it)
            }
        }
    }

}