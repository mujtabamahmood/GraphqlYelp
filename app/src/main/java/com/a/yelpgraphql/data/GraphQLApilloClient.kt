package com.a.yelpgraphql.data

import android.content.Context
import android.util.Log
import com.a.yelpgraphql.R
import com.a.yelpgraphql.SearchQuery
import com.a.yelpgraphql.data.common.BASE_URL
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.api.Input
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

object GraphQLApolloClient {

    private fun apolloClient(context: Context): ApolloClient = ApolloClient.builder()
        .serverUrl(BASE_URL)
        .okHttpClient(
            OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor(context))
            .build()
        ).build()

    fun getBusinesses(term: String , location: String, context: Context) : ApolloQueryCall<SearchQuery.Data> {

        val result = apolloClient(context).query(SearchQuery(Input.optional(term) , Input.optional(location)) )

        Log.d("TAGGQL>", term + ", "+ location+ ", result: "+ result.toString())

        return result
    }

}


private class AuthorizationInterceptor(val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer "+ R.string.api_key)
            .addHeader("Accept-Language", "en_US")
            .addHeader("Content-Type", "application/graphql")
            .build()

        return chain.proceed(request)
    }
}