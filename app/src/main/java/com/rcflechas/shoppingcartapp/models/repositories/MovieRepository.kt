package com.rcflechas.shoppingcartapp.models.repositories

import com.rcflechas.shoppingcartapp.models.data.local.entities.Movie
import com.rcflechas.shoppingcartapp.models.data.remote.responses.MovieResponse
import com.rcflechas.shoppingcartapp.models.data.remote.responses.TheMovieDbResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface MovieRepository {

    fun getAllRemote(): Single<TheMovieDbResponse<MovieResponse>>

    fun getAllLocal(): Flowable<List<Movie>>

    fun insertAllLocal(movies: List<Movie>)

    fun insertLocal(movie: Movie): Completable

    fun getByIdLocal(id: Int): Single<Movie>

    fun deleteByIdLocal(id: Int): Completable

    fun deleteAllLocal(): Completable
}