package com.rcflechas.shoppingcartapp.models.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.rcflechas.shoppingcartapp.views.binds.MovieWithCartBind
import com.rcflechas.shoppingcartapp.views.mappers.BindMapper
import java.io.Serializable

data class CartWithMovieEntity (

    @Embedded val cartEntity: CartEntity,
    @Relation(
        parentColumn = "movi_id",
        entityColumn = "movi_id"
    )
    val movieEntity: MovieEntity

) :  Serializable, BindMapper<CartWithMovieEntity, MovieWithCartBind> {

    override fun toBind() = MovieWithCartBind(
        cart = cartEntity.toBind(),
        movie = movieEntity.toBind()
    )

    override fun List<CartWithMovieEntity>.toListBind() = map( CartWithMovieEntity::toBind )
}