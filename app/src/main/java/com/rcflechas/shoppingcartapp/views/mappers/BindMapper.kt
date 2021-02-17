package com.rcflechas.shoppingcartapp.views.mappers

interface BindMapper <in A, out B> {

    fun toBind() : B
    fun List<A>.toListBind() : List<B>
}