package com.rcflechas.shoppingcartapp.models.data.remote.mappers

interface ResponseMapper <in A, out B> {

    fun toResponse() : B
    fun List<A>.toListResponse() : List<B>
}