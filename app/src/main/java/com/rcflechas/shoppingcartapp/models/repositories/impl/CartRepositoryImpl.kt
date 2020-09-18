package com.rcflechas.shoppingcartapp.models.repositories.impl

import com.rcflechas.shoppingcartapp.models.data.local.dao.CartDAO
import com.rcflechas.shoppingcartapp.models.data.local.entities.Cart
import com.rcflechas.shoppingcartapp.models.data.local.entities.CartMovie
import com.rcflechas.shoppingcartapp.models.data.local.entities.CartMovieWithMovie
import com.rcflechas.shoppingcartapp.models.data.local.entities.CartWithMovie
import com.rcflechas.shoppingcartapp.models.repositories.CartRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class CartRepositoryImpl (private val cartDAO: CartDAO) : CartRepository {

    override fun getCartWithMovieLocal(): Flowable<List<CartWithMovie>> = cartDAO.getCartWithMovie()

    override fun getCartMovieWithMovieLocal(cartId: Int): Flowable<List<CartMovieWithMovie>> = cartDAO.getCartMovieWithMovie(cartId)

    override fun insertLocal(cart: Cart): Completable = cartDAO.insert(cart)

    override fun getByIdLocal(id: Int): Single<Cart> = cartDAO.getById(id)

    override fun deleteByIdLocal(id: Int): Completable = cartDAO.deleteById(id)

    override fun deleteAllLocal(): Completable = cartDAO.deleteAll()

    override fun clearAllCartMovieLocal(cartId: Int): Completable = cartDAO.clearAllCartMovie(cartId)

    override fun clearCartMovieLocal(movieId: Int): Completable = cartDAO.clearCartMovie(movieId)

    override fun updateCartMovieLocal(cartMovie: CartMovie): Completable = cartDAO.updateCartMovie(cartMovie)

    override fun getCartMovieByCartLocal(cartId: Int): Flowable<List<CartMovie>> = cartDAO.getCartMovieByCart(cartId)
}