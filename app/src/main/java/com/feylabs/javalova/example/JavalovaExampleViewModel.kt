package com.feylabs.javalova.example

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

class JavalovaExampleViewModel(
    private val repository : JavalovaRepository,
    private val categoryRepository: CocktailCategoryRepository
    ) : ViewModel() {

    private var _quoteLiveData =
        MutableLiveData<AppResult<List<QuoteApiResponse.QuoteApiResponseItem>>>()
    val quoteLiveData get() = _quoteLiveData

    private var _categoryGlassLiveData =
        MutableLiveData<AppResult<List<DrinkGlassCategoryItem>>>()
    val categoryGlassLiveData get() = _categoryGlassLiveData

    private var _categoryGeneralLiveData =
        MutableLiveData<AppResult<List<DrinkGeneralCategoryItem>>>()
    val categoryGeneralLiveData get() = _categoryGeneralLiveData

    private var _categoryIngredientLiveData =
        MutableLiveData<AppResult<List<DrinkIngredientCategoryItem>>>()
    val categoryIngredientLiveData get() = _categoryIngredientLiveData

    private var _categoryAlcoholicLiveData =
        MutableLiveData<AppResult<List<DrinkAlcoholicCategoryItem>>>()
    val categoryAlcoholicLiveData get() = _categoryAlcoholicLiveData

    fun getQuote() {
        _quoteLiveData.postValue(AppResult.Loading())
        viewModelScope.launch {
            val result =  repository.getAllJavalovaData()
            result.collect{
                _quoteLiveData.postValue(it)
            }
        }
    }

    fun getCategoryIngredient() {
        _categoryIngredientLiveData.postValue(AppResult.Loading())
        viewModelScope.launch {
            val result =  categoryRepository.getIngredientCategory()
            result.collect{
                _categoryIngredientLiveData.postValue(it)
            }
        }
    }

    fun getCategoryGeneral() {
        _categoryGeneralLiveData.postValue(AppResult.Loading())
        viewModelScope.launch {
            val result =  categoryRepository.getGeneralCategory()
            result.collect{
                _categoryGeneralLiveData.postValue(it)
            }
        }
    }

    fun getCategoryAlcoholic() {
        _categoryAlcoholicLiveData.postValue(AppResult.Loading())
        viewModelScope.launch {
            val result =  categoryRepository.getAlcoholic()
            result.collect{
                _categoryAlcoholicLiveData.postValue(it)
            }
        }
    }

    fun getGlassCategory() {
        _categoryGlassLiveData.postValue(AppResult.Loading())
        viewModelScope.launch {
            val result =  categoryRepository.getGlassCategory()
            result.collect{
                _categoryGlassLiveData.postValue(it)
            }
        }
    }
}