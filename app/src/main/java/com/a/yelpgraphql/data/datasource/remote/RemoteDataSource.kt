package com.a.yelpgraphql.data.datasource.remote

import android.content.Context
import com.a.yelpgraphql.SearchQuery
import com.a.yelpgraphql.data.common.YelpResult


interface RemoteDataSource {
    suspend fun getBusinesses(location: String, term: String, context: Context): YelpResult<SearchQuery.Data?>
}