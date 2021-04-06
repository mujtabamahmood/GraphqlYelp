package com.a.yelpgraphql.di

import android.content.Context
import com.a.yelpgraphql.data.datasource.local.AppDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @InternalCoroutinesApi
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) = AppDb.getDatabase(context)


    @Provides
    fun provideAppDAO(db: AppDb) = db.appDao()
}