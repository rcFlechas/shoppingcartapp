package com.rcflechas.shoppingcartapp.models.data.local.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieCart (

    @Embedded val cart: Cart,

    @Relation(
        parentColumn = "cart_id",
        entityColumn = "movi_id",
        associateBy = Junction(MovieCartCrossRef::class)
    )
    val movies: List<Movie>
)