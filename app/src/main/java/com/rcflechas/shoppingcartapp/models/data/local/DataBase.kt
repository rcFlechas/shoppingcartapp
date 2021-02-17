package com.rcflechas.shoppingcartapp.models.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rcflechas.shoppingcartapp.models.data.local.dao.CartDAO
import com.rcflechas.shoppingcartapp.models.data.local.dao.MovieDAO
import com.rcflechas.shoppingcartapp.models.data.local.entities.CartEntity
import com.rcflechas.shoppingcartapp.models.data.local.entities.MovieEntity

@Database(entities = [MovieEntity::class, CartEntity::class], version = 1)
abstract class DataBase : RoomDatabase() {

    abstract fun movieDAO(): MovieDAO
    abstract fun cartDAO(): CartDAO
}
