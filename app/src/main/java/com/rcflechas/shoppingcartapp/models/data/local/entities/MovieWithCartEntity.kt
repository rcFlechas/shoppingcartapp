package com.rcflechas.shoppingcartapp.models.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.rcflechas.shoppingcartapp.views.binds.MovieWithCartBind
import com.rcflechas.shoppingcartapp.views.mappers.BindMapper
import java.io.Serializable

data class MovieWithCartEntity (

    @Embedded val movieEntity: MovieEntity,
    @Relation(
        parentColumn = "movi_id",
        entityColumn = "movi_id"
    )
    val cartEntity: CartEntity?

) : Serializable, BindMapper<MovieWithCartEntity, MovieWithCartBind> {

    override fun toBind() = MovieWithCartBind(
        cart = cartEntity?.toBind() ?: CartEntity().toBind(),
        movie = movieEntity.toBind()
    )

    override fun List<MovieWithCartEntity>.toListBind() = map( MovieWithCartEntity::toBind )
}