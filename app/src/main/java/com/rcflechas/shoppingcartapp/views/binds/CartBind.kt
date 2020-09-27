package com.rcflechas.shoppingcartapp.views.binds

import com.rcflechas.shoppingcartapp.models.data.local.entities.Cart
import java.io.Serializable

data class CartBind(
    val id: Int = 0,
    val movieId: Int = 0,
    val amount: Int = 0
): Serializable {

    companion object{
        fun mapperCartBindToCartEntity(cart: CartBind) = Cart(
            id = cart.id,
            movieId = cart.movieId,
            amount = cart.amount
        )
    }
}