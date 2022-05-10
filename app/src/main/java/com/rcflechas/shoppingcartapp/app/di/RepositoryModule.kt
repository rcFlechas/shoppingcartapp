package com.rcflechas.shoppingcartapp.app.di

import com.rcflechas.shoppingcartapp.models.repositories.CartRepository
import com.rcflechas.shoppingcartapp.models.repositories.MovieRepository
import com.rcflechas.shoppingcartapp.models.repositories.impl.CartRepositoryImpl
import com.rcflechas.shoppingcartapp.models.repositories.impl.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun bindMovieRepository(movieRepository: MovieRepositoryImpl): MovieRepository

    @ViewModelScoped
    @Binds
    abstract fun bindCartRepository(cartRepository: CartRepositoryImpl): CartRepository
}