package com.rcflechas.shoppingcartapp.models.data.remote.mappers

interface RequestMapper <in A, out B> {

    fun toRequest() : B
    fun List<A>.toListRequest() : List<B>
}