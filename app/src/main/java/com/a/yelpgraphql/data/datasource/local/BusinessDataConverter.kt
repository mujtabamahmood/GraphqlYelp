package com.a.yelpgraphql.data.datasource.local

import androidx.room.TypeConverter
import com.a.yelpgraphql.domain.models.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable

class BusinessDataConverter : Serializable {
    private val gson by lazy {Gson()}

    @TypeConverter
    fun fromBusinessesString(value: String): List<BusinessSearchResponseModel>{
        val listType = object: TypeToken<List<BusinessSearchResponseModel>>(){}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromListBusinesses(list: List<BusinessSearchResponseModel>): String{
        return gson.toJson(list)
    }


    @TypeConverter
    fun fromCategoryString(value: String): List<CategoryModel>{
        val listType = object: TypeToken<List<CategoryModel>>(){}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromListCategory(list: List<CategoryModel>): String{
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromPhotoModelString(value: String): List<PhotoModel>{
        val listType = object: TypeToken<List<PhotoModel>>(){}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromListPhotoModel(list: List<PhotoModel>): String{
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromCoordinatesString(value: String): CoordinatesModel {
        val type = object: TypeToken<CoordinatesModel>(){}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromListCoordinates(coordinates: CoordinatesModel): String{
        return gson.toJson(coordinates)
    }


}
