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

    private var _drinkListLiveData =
        MutableLiveData<AppResult<List<DrinkGeneral>>>()
    val drinkListLiveData get() = _drinkListLiveData

    private var _searchLiveData =
        MutableLiveData<AppResult<DrinkDetailList>>()
    val searchLiveData get() = _searchLiveData

    private var _detailLiveData =
        MutableLiveData<AppResult<DrinkDetailList>>()
    val detailLiveData get() = _detailLiveData

    fun search(name:String){
        _searchLiveData.postValue(AppResult.Loading())
        viewModelScope.launch {
            val result = categoryRepository.searchByName(name)
            result.collect{
                _searchLiveData.postValue(it)
            }
        }
    }

    fun detail(id:String){
        _detailLiveData.postValue(AppResult.Loading())
        viewModelScope.launch {
            val result = categoryRepository.lookupById(id)
            result.collect{
                _detailLiveData.postValue(it)
            }
        }
    }

    fun getQuote() {
        _quoteLiveData.postValue(AppResult.Loading())
        viewModelScope.launch {
            val result = repository.getAllJavalovaData()
            result.collect {
                _quoteLiveData.postValue(it)
            }
        }
    }

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

    fun getCategoryIngredient() {
        _categoryIngredientLiveData.postValue(AppResult.Loading())
        viewModelScope.launch {
            val result = categoryRepository.getIngredientCategory()
            result.collect {
                _categoryIngredientLiveData.postValue(it)
            }
        }
    }

    fun getCategoryGeneral() {
        _categoryGeneralLiveData.postValue(AppResult.Loading())
        viewModelScope.launch {
            val result = categoryRepository.getGeneralCategory()
            result.collect {
                _categoryGeneralLiveData.postValue(it)
            }
        }
    }

    fun getCategoryAlcoholic() {
        _categoryAlcoholicLiveData.postValue(AppResult.Loading())
        viewModelScope.launch {
            val result = categoryRepository.getAlcoholic()
            result.collect {
                _categoryAlcoholicLiveData.postValue(it)
            }
        }
    }

    fun getGlassCategory() {
        _categoryGlassLiveData.postValue(AppResult.Loading())
        viewModelScope.launch {
            val result = categoryRepository.getGlassCategory()
            result.collect {
                _categoryGlassLiveData.postValue(it)
            }
        }
    }
}