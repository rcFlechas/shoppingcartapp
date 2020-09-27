package com.rcflechas.shoppingcartapp.models.repositories

import com.rcflechas.shoppingcartapp.models.data.local.entities.Cart
import com.rcflechas.shoppingcartapp.models.data.local.entities.CartWithMovie
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface CartRepository {

    fun getCartWithMovieLocal(): Flowable<List<CartWithMovie>>

    fun insertLocal(cart: Cart): Completable

    fun updateLocal(cart: Cart): Completable

    fun getByIdLocal(id: Int): Single<Cart>

    fun clearCartMovieLocal(movieId: Int): Completable

    fun deleteAllLocal(): Completable
}