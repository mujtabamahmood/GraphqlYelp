package com.a.yelpgraphql.domain.models

data class BusinessSearchResponseModel(

    val total: Int, val business: ArrayList<BusinessModel>

    )