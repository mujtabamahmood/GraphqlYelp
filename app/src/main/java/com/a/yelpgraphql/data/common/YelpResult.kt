package com.a.yelpgraphql.data.common

sealed class YelpResult<out T> {

    data class Success<out T>(val data: T) : YelpResult<T>()
    data class Error(val exception: DataSourceException) : YelpResult<Nothing>()
    object Loading : YelpResult<Nothing>()


}

inline fun <T : Any> YelpResult<T>.onSuccess(action: (T) -> Unit): YelpResult<T> {
    if (this is YelpResult.Success) action(data)
    return this
}

inline fun <T : Any> YelpResult<T>.onError(action: (DataSourceException) -> Unit): YelpResult<T> {
    if (this is YelpResult.Error) action(exception)
    return this
}

inline fun <T : Any> YelpResult<T>.onLoading(action: () -> Unit): YelpResult<T> {
    if (this is YelpResult.Loading) action()
    return this
}
