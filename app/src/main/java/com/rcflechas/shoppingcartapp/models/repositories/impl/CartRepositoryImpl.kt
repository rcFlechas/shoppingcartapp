package com.rcflechas.shoppingcartapp.models.repositories.impl

import com.rcflechas.shoppingcartapp.models.data.local.dao.CartDAO
import com.rcflechas.shoppingcartapp.models.data.local.entities.Cart
import com.rcflechas.shoppingcartapp.models.data.local.entities.CartWithMovie
import com.rcflechas.shoppingcartapp.models.repositories.CartRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class CartRepositoryImpl (private val cartDAO: CartDAO) : CartRepository {

    override fun getCartWithMovieLocal(): Flowable<List<CartWithMovie>> = cartDAO.getCartWithMovie()

    override fun insertLocal(cart: Cart): Completable = cartDAO.insert(cart)

    override fun updateLocal(cart: Cart): Completable = cartDAO.update(cart)

    override fun deleteLocal(cart: Cart): Completable = cartDAO.delete(cart)

    override fun getByIdLocal(id: Int): Single<Cart> = cartDAO.getById(id)

    override fun deleteAllLocal(): Completable = cartDAO.deleteAll()

    override fun clearCartMovieLocal(movieId: Int): Completable = cartDAO.clearCartMovie(movieId)
}