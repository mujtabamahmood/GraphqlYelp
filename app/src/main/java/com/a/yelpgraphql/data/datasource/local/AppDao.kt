package com.a.yelpgraphql.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface AppAdo {

    @Query("SELECT * FROM businesses")
    suspend fun getBusinesses(): List<BusinessModel>

    @Insert(onConflict = REPLACE)
    suspend fun saveBusinesses(list: List<BusinessModel>)
}