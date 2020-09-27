package com.rcflechas.shoppingcartapp.models.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rcflechas.shoppingcartapp.models.data.local.dao.CartDAO
import com.rcflechas.shoppingcartapp.models.data.local.dao.MovieDAO
import com.rcflechas.shoppingcartapp.models.data.local.entities.Cart
import com.rcflechas.shoppingcartapp.models.data.local.entities.Movie

@Database(entities = [Movie::class, Cart::class], version = 1)
abstract class DataBase : RoomDatabase() {

    abstract fun movieDAO(): MovieDAO
    abstract fun cartDAO(): CartDAO
}
