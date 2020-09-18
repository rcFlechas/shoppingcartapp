package com.rcflechas.shoppingcartapp.models.data.remote.api

import com.rcflechas.shoppingcartapp.models.data.remote.responses.MovieResponse
import com.rcflechas.shoppingcartapp.models.data.remote.responses.TheMovieDbResponse
import com.rcflechas.shoppingcartapp.models.data.remote.rest.TheMovieDB
import io.reactivex.Single
import retrofit2.http.GET

interface MovieApi {

    @GET(TheMovieDB.GET_POPULAR)
    fun getPopular(): Single<TheMovieDbResponse<MovieResponse>>
}