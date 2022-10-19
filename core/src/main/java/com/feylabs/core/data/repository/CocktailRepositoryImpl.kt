package com.feylabs.core.data.repository

import android.content.Context
import android.util.Log
import com.feylabs.core.data.local.JavalovaDAO
import com.feylabs.core.data.local.category.DrinkAlcoholicCategoryDAO
import com.feylabs.core.data.local.category.DrinkGeneralCategoryDAO
import com.feylabs.core.data.local.category.DrinkGlassCategoryDAO
import com.feylabs.core.data.local.category.DrinkIngredientCategoryDAO
import com.feylabs.core.data.remote.CocktailDataSource
import com.feylabs.core.data.remote.JavalovaDataSource
import com.feylabs.core.domain.*
import com.feylabs.core.domain.DrinkAlcoholicCategory.DrinkAlcoholicCategoryItem
import com.feylabs.core.domain.DrinkGeneralCategory.DrinkGeneralCategoryItem
import com.feylabs.core.domain.DrinkGlassCategory.DrinkGlassCategoryItem
import com.feylabs.core.domain.DrinkIngredientCategory.DrinkIngredientCategoryItem
import com.feylabs.core.domain.QuoteApiResponse.QuoteApiResponseItem
import com.feylabs.core.util.AppResult
import com.feylabs.core.util.NetworkManager.isOnline
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class CocktailRepositoryImpl(
    private val datasource: CocktailDataSource,
    private val context: Context,
    private val alcoholicCategoryDao: DrinkAlcoholicCategoryDAO,
    private val glassCategoryDao: DrinkGlassCategoryDAO,
    private val ingredientCategoryDao: DrinkIngredientCategoryDAO,
    private val generalCategoryDao: DrinkGeneralCategoryDAO,
) : CocktailRepository {

    override suspend fun getGeneralCategory(): Flow<AppResult<List<DrinkGeneralCategoryItem>>?> {
        return flow {
            emit(AppResult.Loading())
            if (isOnline(context)) {
                fetchGeneralData().collect { data ->
                    if (data is AppResult.Success) {
                        generalCategoryDao.deleteAll()
                        val list = mutableListOf<DrinkGeneralCategoryItem>()
                        data.data?.forEach {
                            it?.let { list.add(it) }
                        }
                        generalCategoryDao.insertAll(list.toList())
                        emit(AppResult.Success())
                    }
                    if (data is AppResult.Error) {
                        emit(AppResult.Error(data.message.toString()))
                    }
                }
                emit(fetchGeneralDataCached())
            } else {
                emit(fetchGeneralDataCached())
            }
        }
    }

    override suspend fun getGlassCategory(): Flow<AppResult<List<DrinkGlassCategoryItem>>?> {
        return flow {
            emit(AppResult.Loading())
            if (isOnline(context)) {
                fetchGlassData().collect { data ->
                    if (data is AppResult.Success) {
                        generalCategoryDao.deleteAll()
                        val list = mutableListOf<DrinkGlassCategoryItem>()
                        data.data?.forEach {
                            it?.let { list.add(it) }
                        }
                        glassCategoryDao.insertAll(list.toList())
                        emit(AppResult.Success())
                    }
                    if (data is AppResult.Error) {
                        emit(AppResult.Error(data.message.toString()))
                    }
                }
                emit(fetchGlassDataCached())
            } else {
                emit(fetchGlassDataCached())
            }
        }
    }

    override suspend fun getIngredientCategory(): Flow<AppResult<List<DrinkIngredientCategoryItem>>?> {
        return flow {
            emit(AppResult.Loading())
            if (isOnline(context)) {
                fetchIngredientData().collect { data ->
                    if (data is AppResult.Success) {
                        ingredientCategoryDao.deleteAll()
                        val list = mutableListOf<DrinkIngredientCategoryItem>()
                        data.data?.forEach {
                            it?.let { list.add(it) }
                        }
                        ingredientCategoryDao.insertAll(list.toList())
                        emit(AppResult.Success())
                    }
                    if (data is AppResult.Error) {
                        emit(AppResult.Error(data.message.toString()))
                    }
                }
                emit(fetchIngredientDataCached())
            } else {
                emit(fetchIngredientDataCached())
            }
        }
    }

    override suspend fun getAlcoholic(): Flow<AppResult<List<DrinkAlcoholicCategoryItem>>?> {
        return flow {
            emit(AppResult.Loading())
            if (isOnline(context)) {
                fetchAlcoholicData().collect { data ->
                    if (data is AppResult.Success) {
                        alcoholicCategoryDao.deleteAll()
                        val list = mutableListOf<DrinkAlcoholicCategoryItem>()
                        data.data?.forEach {
                            it?.let { list.add(it) }
                        }
                        alcoholicCategoryDao.insertAll(list.toList())
                        emit(AppResult.Success())
                    }
                    if (data is AppResult.Error) {
                        emit(AppResult.Error(data.message.toString()))
                    }
                }
                emit(fetchAlcoholicDataCached())
            } else {
                emit(fetchAlcoholicDataCached())
            }
        }
    }

    private fun fetchAlcoholicDataCached(): AppResult<List<DrinkAlcoholicCategoryItem>>? =
        alcoholicCategoryDao.getAll()?.let {
            AppResult.Success((it))
        }
    private fun fetchGeneralDataCached(): AppResult<List<DrinkGeneralCategoryItem>>? =
        generalCategoryDao.getAll()?.let {
            AppResult.Success((it))
        }
    private fun fetchGlassDataCached(): AppResult<List<DrinkGlassCategoryItem>>? =
        glassCategoryDao.getAll()?.let {
            AppResult.Success((it))
        }
    private fun fetchIngredientDataCached(): AppResult<List<DrinkIngredientCategoryItem>>? =
        ingredientCategoryDao.getAll()?.let {
            AppResult.Success((it))
        }


    private suspend fun fetchAlcoholicData() = datasource.getAlcoholicCategory()
    private suspend fun fetchIngredientData() = datasource.getIngredientCategory()
    private suspend fun fetchGlassData() = datasource.getGlassCategory()
    private suspend fun fetchGeneralData() = datasource.getGeneralCategory()


}