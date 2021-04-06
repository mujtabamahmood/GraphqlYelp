package com.a.yelpgraphql.ui.search

import android.content.Context
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a.yelpgraphql.data.common.YelpResult
import com.a.yelpgraphql.domain.models.BusinessSearchResponseModel
import com.a.yelpgraphql.domain.usecases.GetBusinessesUseCases
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class BusinessesViewModel  @ViewModelInject constructor(
    private val getBusinessesUseCases: GetBusinessesUseCases
) : ViewModel() {
    private val _resultListBusinesses = MutableLiveData<YelpResult<BusinessSearchResponseModel>>()
    val resultBusinesses : LiveData<YelpResult<BusinessSearchResponseModel>> = _resultListBusinesses

    fun getListBusinesses(term: String, location: String, context: Context){
        Log.d("TAG", "inside getListBusiness "+term)
        viewModelScope.launch {
            Log.d("TAG", "inside getListBusiness launch"+term)

            getBusinessesUseCases.invoke(term, location, context).collect {
                Log.d("TAG", "inside getListBusiness ")

                _resultListBusinesses.postValue(it)
            }
        }
    }
}