package com.a.yelpgraphql.data.datasource.remote

import android.content.Context
import android.util.Log
import com.a.yelpgraphql.R
import com.a.yelpgraphql.SearchQuery
import com.a.yelpgraphql.data.GraphQLApolloClient
import com.a.yelpgraphql.data.common.DataSourceException
import com.a.yelpgraphql.data.common.YelpResult
import com.apollographql.apollo.coroutines.await
import java.lang.Exception

class RemoteDataSourceImpl: RemoteDataSource {
    override suspend fun getBusinesses(location: String, term: String, context: Context): YelpResult<SearchQuery.Data?> {
       return try{
            val result = GraphQLApolloClient.getBusinesses(location, term, context)?.await()
           if(result?.hasErrors()!!){
               Log.d("Error>>", result?.errors.toString())
               YelpResult.Error(DataSourceException.Server(result?.errors?.first()))
           }else{
               YelpResult.Success(result?.data)
           }
        }catch(e: Exception){
           Log.d("Error>>", e.message.toString())

           YelpResult.Error(DataSourceException.Unexpected(R.string.unexpected_error_message))
        }
    }
}