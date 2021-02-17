package com.rcflechas.shoppingcartapp.models.data.local.dao

import androidx.room.*
import com.rcflechas.shoppingcartapp.models.data.local.entities.MovieEntity
import com.rcflechas.shoppingcartapp.models.data.local.entities.MovieWithCartEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface MovieDAO {

    @Query("SELECT * FROM movieEntity")
    fun getAll(): Flowable<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<MovieEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieEntity: MovieEntity): Completable

    @Query("SELECT * FROM movieEntity WHERE movi_id = :id")
    fun getById(id: Int): Single<MovieEntity>

    @Query("DELETE FROM movieEntity WHERE movi_id = :id")
    fun deleteById(id: Int): Completable

    @Query("DELETE FROM movieEntity")
    fun deleteAll(): Completable

    @Transaction
    @Query("SELECT * FROM movieEntity")
    fun getMovieWithCart(): Flowable<List<MovieWithCartEntity>>
}