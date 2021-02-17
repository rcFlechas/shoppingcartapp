package com.rcflechas.shoppingcartapp.models.data.local.dao

import androidx.room.*
import com.rcflechas.shoppingcartapp.models.data.local.entities.CartEntity
import com.rcflechas.shoppingcartapp.models.data.local.entities.CartWithMovieEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface CartDAO {

    @Transaction
    @Query("SELECT * FROM cartEntity")
    fun getCartWithMovie(): Flowable<List<CartWithMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cartEntity: CartEntity): Completable

    @Update
    fun update(cartEntity: CartEntity): Completable

    @Delete
    fun delete(cartEntity: CartEntity): Completable

    @Query("SELECT * FROM cartEntity WHERE cart_id = :id")
    fun getById(id: Int): Single<CartEntity>

    @Query("DELETE FROM cartEntity WHERE movi_id = :movieId")
    fun clearCartMovie(movieId: Int): Completable

    @Query("DELETE FROM cartEntity")
    fun deleteAll(): Completable
}