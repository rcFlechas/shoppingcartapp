package com.rcflechas.shoppingcartapp.app.di

import com.rcflechas.shoppingcartapp.core.RetrofitFactory
import com.rcflechas.shoppingcartapp.models.data.remote.api.MovieApi
import com.rcflechas.shoppingcartapp.models.data.remote.rest.TheMovieDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {

    @Provides
    fun provideMovieApi(): MovieApi {
        return RetrofitFactory.retrofit(TheMovieDB.URL_BASE).create(MovieApi::class.java)
    }
}