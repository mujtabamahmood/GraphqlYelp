package com.a.yelpgraphql.domain.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity(tableName = "businesses", primaryKeys = ["id"])
@Parcelize
data class BusinessModel(
    val categories: List<CategoryModel>,
    val coordinates: CoordinatesModel,
    val id: String,
    val name: String,
    val photos: List<PhotoModel>,
    val price: String,
    val rating: Int
): Parcelable