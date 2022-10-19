package com.feylabs.core.data.repository

import com.feylabs.core.domain.QuoteApiResponse
import com.feylabs.core.util.AppResult
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

interface JavalovaRepository {
    suspend fun getAllJavalovaData() : Flow<AppResult<List<QuoteApiResponse.QuoteApiResponseItem>>?>
}
