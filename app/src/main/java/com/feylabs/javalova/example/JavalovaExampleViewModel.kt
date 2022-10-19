package com.feylabs.javalova.example

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feylabs.core.data.repository.JavalovaRepository
import com.feylabs.core.domain.QuoteApiResponse
import com.feylabs.core.util.AppResult
import kotlinx.coroutines.launch

class JavalovaExampleViewModel(private val repository : JavalovaRepository) : ViewModel() {

    private var _quoteLiveData =
        MutableLiveData<AppResult<List<QuoteApiResponse.QuoteApiResponseItem>>>()
    val quoteLiveData get() = _quoteLiveData

    fun getQuote() {
        _quoteLiveData.postValue(AppResult.Loading())
        viewModelScope.launch {
            val result =  repository.getAllJavalovaData()
            result.collect{
                _quoteLiveData.postValue(it)
            }
        }
    }
}