package com.a.yelpgraphql.domain.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity(tableName = "coordinate", primaryKeys = ["id"])
@Parcelize
data class CoordinatesModel(
    val latitude: Double?,
    val longitude: Double?
): Parcelable {
    constructor() : this(0.0, 0.0)
}