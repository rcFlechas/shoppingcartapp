package com.rcflechas.shoppingcartapp.models.repositories.impl

import com.rcflechas.shoppingcartapp.models.data.local.dao.MovieDAO
import com.rcflechas.shoppingcartapp.models.data.remote.api.MovieApi
import com.rcflechas.shoppingcartapp.models.repositories.TmdbRepository

class TmdbRepositoryImpl(private val tmdbApi: MovieApi, private val tmdbDAO: MovieDAO) : TmdbRepository {


    companion object {
        const val TAG = "TmdbRepositoryImpl"
    }
}