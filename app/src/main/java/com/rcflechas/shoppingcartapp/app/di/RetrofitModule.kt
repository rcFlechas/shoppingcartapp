package com.rcflechas.shoppingcartapp.app.di

import com.rcflechas.shoppingcartapp.core.RetrofitFactory
import com.rcflechas.shoppingcartapp.models.data.remote.api.MovieApi
import com.rcflechas.shoppingcartapp.models.data.remote.rest.TheMovieDB
import org.koin.dsl.module

val retrofitModule = module {
    single { RetrofitFactory.retrofit(TheMovieDB.URL_BASE).create(MovieApi::class.java) }
}