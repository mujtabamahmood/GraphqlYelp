package com.a.yelpgraphql.domain.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity(tableName = "category", primaryKeys = ["title"])
@Parcelize
data class CategoryModel(
    val title: String
) : Parcelable