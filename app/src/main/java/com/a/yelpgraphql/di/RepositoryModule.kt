package com.a.yelpgraphql.di

import com.a.yelpgraphql.data.datasource.local.AppDao
import com.a.yelpgraphql.data.datasource.remote.RemoteDataSourceImpl
import com.a.yelpgraphql.domain.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAppRepository(appDao: AppDao): AppRepository{
        val remoteDataSource = RemoteDataSourceImpl()
        return com.a.yelpgraphql.data.repository.AppRepositoryImpl(remoteDataSource, appDao)
    }
}