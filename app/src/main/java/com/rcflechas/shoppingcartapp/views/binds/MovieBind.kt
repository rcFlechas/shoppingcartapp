package com.rcflechas.shoppingcartapp.views.binds

import java.io.Serializable

data class MovieBind(
    
    val id: Int = 0,
    val title: String = String(),
    val overView: String,
    val posterPath: String = String(),
    val backdropPath: String = String(),
    val isAdult: Boolean = false
): Serializable