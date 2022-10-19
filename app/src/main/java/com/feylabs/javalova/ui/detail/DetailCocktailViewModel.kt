package com.feylabs.javalova.ui.detail

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

class DetailCocktailViewModel(
    private val repository: JavalovaRepository,
    private val categoryRepository: CocktailCategoryRepository
) : ViewModel() {


    private var _detailLiveData =
        MutableLiveData<AppResult<DrinkDetailList>>()
    val detailLiveData get() = _detailLiveData

    fun detail(id:String){
        _detailLiveData.postValue(AppResult.Loading())
        viewModelScope.launch {
            val result = categoryRepository.lookupById(id)
            result.collect{
                _detailLiveData.postValue(it)
            }
        }
    }
}