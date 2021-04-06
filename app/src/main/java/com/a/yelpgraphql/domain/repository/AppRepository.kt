package com.a.yelpgraphql.domain.repository

import android.content.Context
import com.a.yelpgraphql.data.common.YelpResult
import com.a.yelpgraphql.domain.models.BusinessSearchResponseModel
import kotlinx.coroutines.flow.Flow


interface AppRepository {
    suspend fun getBusinesses(term: String, location: String, context: Context) : Flow<YelpResult<BusinessSearchResponseModel>>
}