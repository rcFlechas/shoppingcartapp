package com.rcflechas.shoppingcartapp.app.di

import androidx.room.Room
import com.rcflechas.shoppingcartapp.models.data.local.DataBase
import org.koin.dsl.module

val roomModule = module {
    single { Room.databaseBuilder(get(), DataBase::class.java, "database_movies").build() }
    single { get<DataBase>().movieDAO() }
    single { get<DataBase>().cartDAO() }
}