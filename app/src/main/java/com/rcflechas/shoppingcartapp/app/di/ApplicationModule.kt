package com.rcflechas.shoppingcartapp.app.di

import com.rcflechas.shoppingcartapp.models.repositories.CartRepository
import com.rcflechas.shoppingcartapp.models.repositories.MovieRepository
import com.rcflechas.shoppingcartapp.models.repositories.impl.CartRepositoryImpl
import com.rcflechas.shoppingcartapp.models.repositories.impl.MovieRepositoryImpl
import org.koin.dsl.module

val applicationModule = module {
    factory<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    factory<CartRepository> { CartRepositoryImpl(get()) }
}