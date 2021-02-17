package com.rcflechas.shoppingcartapp.models.repositories

import com.rcflechas.shoppingcartapp.models.data.local.entities.CartEntity
import com.rcflechas.shoppingcartapp.models.data.local.entities.CartWithMovieEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface CartRepository {

    fun getCartWithMovieLocal(): Flowable<List<CartWithMovieEntity>>

    fun insertLocal(cartEntity: CartEntity): Completable

    fun updateLocal(cartEntity: CartEntity): Completable

    fun deleteLocal(cartEntity: CartEntity): Completable

    fun getByIdLocal(id: Int): Single<CartEntity>

    fun clearCartMovieLocal(movieId: Int): Completable

    fun deleteAllLocal(): Completable
}