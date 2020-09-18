package com.rcflechas.shoppingcartapp.models.data.remote.responses

import androidx.annotation.Keep
import com.squareup.moshi.Json
import java.io.Serializable

@Keep
data class TheMovieDbResponse<T> (
    @Json(name = "page")
    @field:Json(name = "page")
    val page: Int = 0,

    @Json(name = "total_results")
    @field:Json(name = "total_results")
    val totalResults: Int = 0,

    @Json(name = "total_pages")
    @field:Json(name = "total_pages")
    val totalPages: Int = 0,

    @Json(name = "results")
    @field:Json(name = "results")
    val results: List<T> = listOf()
): Serializable