package com.rcflechas.shoppingcartapp.views.binds

import com.rcflechas.shoppingcartapp.models.data.local.entities.CartEntity
import com.rcflechas.shoppingcartapp.models.data.local.mappers.EntityMapper
import java.io.Serializable

data class CartBind(

    val id: Int = 0,
    val movieId: Int = 0,
    var amount: Int = 0

): Serializable, EntityMapper<CartBind, CartEntity> {

    override fun toEntity() = CartEntity(
        id = this.id,
        movieId = this.movieId,
        amount = this.amount
    )

    override fun List<CartBind>.toListEntity() = map( CartBind::toEntity )
}