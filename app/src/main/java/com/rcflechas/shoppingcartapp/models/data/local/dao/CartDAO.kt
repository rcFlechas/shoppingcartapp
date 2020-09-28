package com.rcflechas.shoppingcartapp.models.data.local.dao

import androidx.room.*
import com.rcflechas.shoppingcartapp.models.data.local.entities.Cart
import com.rcflechas.shoppingcartapp.models.data.local.entities.CartWithMovie
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface CartDAO {

    @Transaction
    @Query("SELECT * FROM cart")
    fun getCartWithMovie(): Flowable<List<CartWithMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cart: Cart): Completable

    @Update
    fun update(cart: Cart): Completable

    @Delete
    fun delete(cart: Cart): Completable

    @Query("SELECT * FROM cart WHERE cart_id = :id")
    fun getById(id: Int): Single<Cart>

    @Query("DELETE FROM cart WHERE movi_id = :movieId")
    fun clearCartMovie(movieId: Int): Completable

    @Query("DELETE FROM cart")
    fun deleteAll(): Completable
}