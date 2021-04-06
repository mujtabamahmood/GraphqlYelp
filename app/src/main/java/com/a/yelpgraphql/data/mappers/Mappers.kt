package com.a.yelpgraphql.data.mappers

import com.a.yelpgraphql.SearchQuery
import com.a.yelpgraphql.domain.models.*

fun SearchQuery.Business.mapToDomainModel() = BusinessModel(
    categories?.map { it!!.mapToDomainModel() } ?: emptyList(),
    coordinates?.mapToDomainModel() ?: CoordinatesModel(),
 id ?: "",
 name ?: "",
    (photos ?: emptyList()) as List<PhotoModel>,
 price ?: "",
    (rating ?: 0) as Int,
)

fun SearchQuery.Category.mapToDomainModel() = CategoryModel(title ?: "")
fun SearchQuery.Data.mapToDomainModel() = BusinessSearchResponseModel(
    0,
    emptyList<BusinessModel>() as ArrayList<BusinessModel>
)
fun SearchQuery.Coordinates.mapToDomainModel() = CoordinatesModel(latitude ?: 0.0, longitude ?: 0.0)