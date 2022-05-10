package com.rcflechas.shoppingcartapp.core

import com.rcflechas.shoppingcartapp.BuildConfig
import com.rcflechas.shoppingcartapp.models.data.remote.rest.TheMovieDB
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {

    private val moshi = Moshi.Builder().build()

    private val authInterceptor = Interceptor {chain->
        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("api_key", TheMovieDB.TMDB_API_KEY)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    private val loggingInterceptor =  HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client =
        if(BuildConfig.DEBUG){

            OkHttpClient.Builder().apply {
                addInterceptor(authInterceptor)
                addInterceptor(loggingInterceptor)
                connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                retryOnConnectionFailure(true)
            }.build()
        }else{

            OkHttpClient.Builder().apply {
                addInterceptor(loggingInterceptor)
                addInterceptor(authInterceptor)
                connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                retryOnConnectionFailure(true)
            }.build()
        }

    fun retrofit(baseUrl : String) : Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()


    private const val CONNECT_TIMEOUT = 15L
    private const val WRITE_TIMEOUT = 15L
    private const val READ_TIMEOUT = 15L
}