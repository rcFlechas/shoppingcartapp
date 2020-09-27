package com.rcflechas.shoppingcartapp.app.di

import com.rcflechas.shoppingcartapp.viewmodels.CartViewModel
import com.rcflechas.shoppingcartapp.viewmodels.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel(get(), get()) }
    viewModel { CartViewModel(get()) }
}