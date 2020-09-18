package com.rcflechas.shoppingcartapp.models.repositories.impl

import android.annotation.SuppressLint
import android.util.Log
import com.rcflechas.shoppingcartapp.models.data.local.dao.MovieDAO
import com.rcflechas.shoppingcartapp.models.data.local.entities.Movie
import com.rcflechas.shoppingcartapp.models.data.remote.api.MovieApi
import com.rcflechas.shoppingcartapp.models.data.remote.responses.MovieResponse
import com.rcflechas.shoppingcartapp.models.data.remote.responses.TheMovieDbResponse
import com.rcflechas.shoppingcartapp.models.repositories.MovieRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MovieRepositoryImpl(private val movieApi: MovieApi, private val movieDAO: MovieDAO) : MovieRepository {

    override fun getAllRemote(): Single<TheMovieDbResponse<MovieResponse>> = movieApi.getPopular()

    override fun getAllLocal(): Flowable<List<Movie>> = movieDAO.getAll()

    @SuppressLint("CheckResult")
    override fun insertAllLocal(movies: List<Movie>) {
        movieDAO.insertAll(movies)
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onComplete = {
                    Log.i(TAG, "--- onComplete insert")
                },
                onError = {
                    Log.i(TAG, "--- ${it.message}")
                }
            )
    }

    override fun insertLocal(movie: Movie): Completable = movieDAO.insert(movie)

    override fun getByIdLocal(id: Int): Single<Movie> = movieDAO.getById(id)

    override fun deleteByIdLocal(id: Int): Completable = movieDAO.deleteById(id)

    override fun deleteAllLocal(): Completable = movieDAO.deleteAll()

    companion object {
        const val TAG = "MovieRepositoryImpl"
    }
}