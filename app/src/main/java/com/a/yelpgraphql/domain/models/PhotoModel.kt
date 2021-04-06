package com.a.yelpgraphql.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize

@Entity(tableName = "photos", primaryKeys = ["photo"])
@Parcelize
data class  PhotoModel (
    val photo: String
    ): Parcelable
