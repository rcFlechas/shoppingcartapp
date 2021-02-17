package com.rcflechas.shoppingcartapp.views.binds

import java.io.Serializable

data class MovieWithCartBind (
    val cart: CartBind,
    val movie: MovieBind
) : Serializable