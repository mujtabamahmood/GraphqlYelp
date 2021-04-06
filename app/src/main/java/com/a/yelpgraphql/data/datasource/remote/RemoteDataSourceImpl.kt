package com.a.yelpgraphql.data.datasource.remote

import com.a.yelpgraphql.SearchYelpQuery
import com.a.yelpgraphql.data.GraphQLApolloClient
import com.a.yelpgraphql.data.common.YelpResult
import com.apollographql.apollo.coroutines.await

class RemteDataSourceImpl: RemoteDataSource {
    override suspend fun getBusinesses(location: String): YelpResult<SearchYelpQuery.Search?> {
       return try{
            val result = GraphQLApolloClient.getBusinesses().await
        }
    }
}