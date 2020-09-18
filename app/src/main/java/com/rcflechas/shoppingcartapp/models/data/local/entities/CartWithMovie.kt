package com.rcflechas.shoppingcartapp.models.data.local.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CartWithMovie (

    @Embedded val cart: Cart,
    @Relation(
        parentColumn = "cart_id",
        entityColumn = "camo_id",
        associateBy = Junction(CartMovieCrossRef::class)
    )
    val cartMovies: List<CartMovie>
)