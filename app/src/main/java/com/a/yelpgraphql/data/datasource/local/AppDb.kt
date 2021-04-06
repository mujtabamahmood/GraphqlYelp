package com.a.yelpgraphql.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.a.yelpgraphql.BuildConfig
import com.a.yelpgraphql.domain.models.BusinessModel
import com.a.yelpgraphql.domain.models.CategoryModel
import com.a.yelpgraphql.domain.models.PhotoModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [BusinessModel::class, CategoryModel::class, PhotoModel::class], version = 1, exportSchema = false)
@TypeConverters(BusinessDataConverter::class)
abstract class AppDb : RoomDatabase(){

    abstract fun appDao(): AppDao

    companion object{

        @Volatile
        private var INSTANCE: AppDb? = null

        @InternalCoroutinesApi
        fun getDatabase(context: Context): AppDb {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDb::class.java,
                    BuildConfig.APPLICATION_ID
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }

    }


}
