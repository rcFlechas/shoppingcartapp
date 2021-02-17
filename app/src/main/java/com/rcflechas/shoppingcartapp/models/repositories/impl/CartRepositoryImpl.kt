package com.rcflechas.shoppingcartapp.models.repositories.impl

import com.rcflechas.shoppingcartapp.models.data.local.dao.CartDAO
import com.rcflechas.shoppingcartapp.models.data.local.entities.CartEntity
import com.rcflechas.shoppingcartapp.models.data.local.entities.CartWithMovieEntity
import com.rcflechas.shoppingcartapp.models.repositories.CartRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class CartRepositoryImpl (private val cartDAO: CartDAO) : CartRepository {

    override fun getCartWithMovieLocal(): Flowable<List<CartWithMovieEntity>> = cartDAO.getCartWithMovie()

    override fun insertLocal(cartEntity: CartEntity): Completable = cartDAO.insert(cartEntity)

    override fun updateLocal(cartEntity: CartEntity): Completable = cartDAO.update(cartEntity)

    override fun deleteLocal(cartEntity: CartEntity): Completable = cartDAO.delete(cartEntity)

    override fun getByIdLocal(id: Int): Single<CartEntity> = cartDAO.getById(id)

    override fun deleteAllLocal(): Completable = cartDAO.deleteAll()

    override fun clearCartMovieLocal(movieId: Int): Completable = cartDAO.clearCartMovie(movieId)
}