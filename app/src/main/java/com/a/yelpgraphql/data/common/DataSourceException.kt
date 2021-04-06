package com.a.yelpgraphql.data.common

import com.apollographql.apollo.api.Error
import java.lang.RuntimeException

sealed class DataSourceException(
    val messageResource: Any?
) : RuntimeException() {
    class Unexpected(messageSource: Int) : DataSourceException(messageSource)
    class Server(error: Error?) : DataSourceException(error)
}
