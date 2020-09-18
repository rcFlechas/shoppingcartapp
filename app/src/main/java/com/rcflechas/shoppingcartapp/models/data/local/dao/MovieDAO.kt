package com.rcflechas.shoppingcartapp.models.data.local.dao

import androidx.room.*
import com.rcflechas.shoppingcartapp.models.data.local.entities.Movie
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface MovieDAO {

    @Query("SELECT * FROM movie")
    fun getAll(): Flowable<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<Movie>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie): Completable

    @Query("SELECT * FROM movie WHERE movi_id = :id")
    fun getById(id: Int): Single<Movie>

    @Query("DELETE FROM movie WHERE movi_id = :id")
    fun deleteById(id: Int): Completable

    @Query("DELETE FROM movie")
    fun deleteAll(): Completable
}