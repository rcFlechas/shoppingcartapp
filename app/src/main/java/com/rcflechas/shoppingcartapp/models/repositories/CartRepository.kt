package com.rcflechas.shoppingcartapp.models.repositories

import com.rcflechas.shoppingcartapp.models.data.local.entities.Cart
import com.rcflechas.shoppingcartapp.models.data.local.entities.CartMovie
import com.rcflechas.shoppingcartapp.models.data.local.entities.CartMovieWithMovie
import com.rcflechas.shoppingcartapp.models.data.local.entities.CartWithMovie
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface CartRepository {

    fun getCartWithMovieLocal(): Flowable<List<CartWithMovie>>

    fun getCartMovieWithMovieLocal(cartId: Int): Flowable<List<CartMovieWithMovie>>

    fun insertLocal(cart: Cart): Completable

    fun getByIdLocal(id: Int): Single<Cart>

    fun deleteByIdLocal(id: Int): Completable

    fun deleteAllLocal(): Completable

    fun clearAllCartMovieLocal(cartId: Int): Completable

    fun clearCartMovieLocal(movieId: Int): Completable

    fun updateCartMovieLocal(cartMovie: CartMovie): Completable

    fun getCartMovieByCartLocal(cartId: Int): Flowable<List<CartMovie>>
}