package com.a.yelpgraphql.domain.usecases

import com.a.yelpgraphql.domain.repository.AppRepository
import javax.inject.Inject

class GetBusinessesUseCases @Inject constructor(
    private val appRepository: AppRepository
){
    suspend operator fun invoke(
        term: kotlin.String,
        location: kotlin.String,
        context: android.content.Context
    ) = appRepository.getBusinesses(term, location, context)
}