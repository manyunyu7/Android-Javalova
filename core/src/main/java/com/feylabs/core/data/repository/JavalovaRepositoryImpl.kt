package com.feylabs.core.data.repository

import android.content.Context
import android.util.Log
import com.feylabs.core.data.local.JavalovaDAO
import com.feylabs.core.data.remote.JavalovaDataSource
import com.feylabs.core.domain.QuoteApiResponse
import com.feylabs.core.domain.QuoteApiResponse.QuoteApiResponseItem
import com.feylabs.core.util.AppResult
import com.feylabs.core.util.NetworkManager.isOnline
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class JavalovaRepositoryImpl(
    private val datasource: JavalovaDataSource,
    private val context: Context,
    private val dao: JavalovaDAO
) : JavalovaRepository {

    override suspend fun getAllJavalovaData(): Flow<AppResult<List<QuoteApiResponseItem>>?> {
        return flow {
            emit(AppResult.Loading())
            if (isOnline(context)) {
                fetchJavalova().collect { data ->
                    if (data is AppResult.Success) {
                        dao.deleteAll()
                        val list = mutableListOf<QuoteApiResponseItem>()
                        data.data?.forEach {
                            it?.let { list.add(it) }
                        }
                        dao.insertAll(list.toList())
                        emit(AppResult.Success())
                    }
                    if (data is AppResult.Error) {
                        emit(AppResult.Error("Salamualaikum"))
                    }
                }
                emit(fetchJavalovaCached())
            } else {
                emit(AppResult.Error("Salamualaikum"))
            }
        }.flowOn(Dispatchers.IO)
    }

    private fun fetchJavalovaCached(): AppResult<List<QuoteApiResponseItem>>? =
        dao.getAll()?.let {
            AppResult.Success((it))
        }

    suspend fun fetchJavalova(): Flow<AppResult<List<QuoteApiResponseItem?>>> {
        return datasource.getJavalovaData()
    }


}