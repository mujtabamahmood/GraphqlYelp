package com.a.yelpgraphql.data.repository

import android.content.Context
import android.util.Log
import com.a.yelpgraphql.data.common.YelpResult
import com.a.yelpgraphql.data.datasource.local.AppDao
import com.a.yelpgraphql.data.datasource.remote.RemoteDataSource

import com.a.yelpgraphql.domain.models.BusinessSearchResponseModel
import com.a.yelpgraphql.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow



class AppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val appDao: AppDao
): AppRepository {
    override suspend fun getBusinesses(
        term: String,
        location: String,
        context: Context
    ): Flow<YelpResult<BusinessSearchResponseModel>> =

    flow {

        Log.d("TAG", "apprepository " + term)
        when (val result = remoteDataSource.getBusinesses(term, location, context)){

            is YelpResult.Success -> {
                Log.d("TAG", result.toString())
                result.data?.let {
                    Log.d("TAG", result.toString())
                }
            }
            is YelpResult.Error ->{
                Log.d("TAG error", "Error: " + result.exception)
            }
        }
    }
}
