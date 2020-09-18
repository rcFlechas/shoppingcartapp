package com.rcflechas.shoppingcartapp.models.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CartMovieWithMovie(
    @Embedded val cartMovie: CartMovie,
    @Relation(
        entity = Movie::class,
        parentColumn = "movi_id",
        entityColumn = "movi_id"
    )
    val movies: List<Movie>
)