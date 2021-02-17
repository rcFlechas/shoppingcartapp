package com.rcflechas.shoppingcartapp.models.repositories

import com.rcflechas.shoppingcartapp.models.data.local.entities.MovieEntity
import com.rcflechas.shoppingcartapp.models.data.local.entities.MovieWithCartEntity
import com.rcflechas.shoppingcartapp.models.data.remote.responses.MovieResponse
import com.rcflechas.shoppingcartapp.models.data.remote.responses.TheMovieDbResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface MovieRepository {

    fun getAllRemote(): Single<TheMovieDbResponse<MovieResponse>>

    fun getAllLocal(): Flowable<List<MovieEntity>>

    fun insertAllLocal(movies: List<MovieEntity>)

    fun insertLocal(movieEntity: MovieEntity): Completable

    fun getByIdLocal(id: Int): Single<MovieEntity>

    fun deleteByIdLocal(id: Int): Completable

    fun deleteAllLocal(): Completable

    fun getMovieWithCartLocal(): Flowable<List<MovieWithCartEntity>>
}