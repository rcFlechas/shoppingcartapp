package com.rcflechas.shoppingcartapp.models.data.local.dao

import androidx.room.*
import com.rcflechas.shoppingcartapp.models.data.local.entities.Cart
import com.rcflechas.shoppingcartapp.models.data.local.entities.CartMovie
import com.rcflechas.shoppingcartapp.models.data.local.entities.CartMovieWithMovie
import com.rcflechas.shoppingcartapp.models.data.local.entities.CartWithMovie
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface CartDAO {

    @Transaction
    @Query("SELECT * FROM cart")
    fun getCartWithMovie(): Flowable<List<CartWithMovie>>

    @Transaction
    @Query("SELECT * FROM cart_movie WHERE cart_id = :cartId")
    fun getCartMovieWithMovie(cartId: Int): Flowable<List<CartMovieWithMovie>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cart: Cart): Completable

    @Query("SELECT * FROM cart WHERE cart_id = :id")
    fun getById(id: Int): Single<Cart>

    @Query("DELETE FROM cart WHERE cart_id = :id")
    fun deleteById(id: Int): Completable

    @Query("DELETE FROM cart")
    fun deleteAll(): Completable

    @Query("DELETE FROM cart_movie WHERE cart_id = :cartId")
    fun clearAllCartMovie(cartId: Int): Completable

    @Query("DELETE FROM cart_movie WHERE movi_id = :movieId")
    fun clearCartMovie(movieId: Int): Completable

    @Update
    fun updateCartMovie(cartMovie: CartMovie): Completable

    @Query("SELECT * FROM cart_movie WHERE cart_id = :cartId")
    fun getCartMovieByCart(cartId: Int): Flowable<List<CartMovie>>
}