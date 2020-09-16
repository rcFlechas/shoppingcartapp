package com.rcflechas.shoppingcartapp.models.data.local.dao

import androidx.room.*

@Dao
interface MovieDAO {

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(post: List<Post>): Completable

    @Query("SELECT * FROM post")
    fun getAll(): Flowable<List<Post>>

    @Update
    fun read(post: Post): Completable

    @Update
    fun deleteFavorite(post: Post): Completable

    @Update
    fun addFavorite(post: Post): Completable

    @Query("SELECT * FROM post WHERE isFavorite = 1")
    fun getAllFavorite(): Flowable<List<Post>>

    @Query("DELETE FROM post")
    fun deleteAll(): Completable

    @Query("DELETE FROM post WHERE id = :id")
    fun deleteById(id: Int): Completable*/
}