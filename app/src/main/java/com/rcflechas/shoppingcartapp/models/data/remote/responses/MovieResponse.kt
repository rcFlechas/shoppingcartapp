package com.rcflechas.shoppingcartapp.models.data.remote.responses

import androidx.annotation.Keep
import com.rcflechas.shoppingcartapp.models.data.local.entities.MovieEntity
import com.rcflechas.shoppingcartapp.models.data.local.mappers.EntityMapper
import com.rcflechas.shoppingcartapp.views.binds.MovieBind
import com.rcflechas.shoppingcartapp.views.mappers.BindMapper
import com.squareup.moshi.Json
import java.io.Serializable

@Keep
data class MovieResponse(

    @Json(name = "id")
    @field:Json(name = "id")
    val id: Int = 0,

    @Json(name = "title")
    @field:Json(name = "title")
    val title: String = String(),

    @Json(name = "poster_path")
    @field:Json(name = "poster_path")
    val posterPath: String = String(),

    @Json(name = "adult")
    @field:Json(name = "adult")
    val isAdult: Boolean = false,

    @Json(name = "overview")
    @field:Json(name = "overview")
    val overView: String,

    @Json(name = "release_date")
    @field:Json(name = "release_date")
    val releaseDate: String = String(),

    @Json(name = "genre_ids")
    @field:Json(name = "genre_ids")
    val genreIds: Array<Int>,

    @Json(name = "original_title")
    @field:Json(name = "original_title")
    val originalTitle: String = String(),

    @Json(name = "original_language")
    @field:Json(name = "original_language")
    val originalLanguage: String = String(),

    @Json(name = "backdrop_path")
    @field:Json(name = "backdrop_path")
    val backdropPath: String = String(),

    @Json(name = "popularity")
    @field:Json(name = "popularity")
    val popularity: Double = 0.0,

    @Json(name = "vote_count")
    @field:Json(name = "vote_count")
    val voteCount: Int = 0,

    @Json(name = "video")
    @field:Json(name = "video")
    val isVideo: Boolean = false,

    @Json(name = "vote_average")
    @field:Json(name = "vote_average")
    val voteAverage: Double = 0.0

): Serializable, BindMapper<MovieResponse,MovieBind>, EntityMapper<MovieResponse, MovieEntity> {

    override fun toBind() = MovieBind(
        id = id,
        title = title,
        overView = overView,
        posterPath = posterPath,
        backdropPath = backdropPath,
        isAdult = isAdult
    )
    override fun List<MovieResponse>.toListBind() = map( MovieResponse::toBind)

    override fun toEntity() = MovieEntity(
        id = id,
        title = title,
        overView = overView,
        posterPath = posterPath,
        backdropPath = backdropPath,
        isAdult = isAdult
    )
    override fun List<MovieResponse>.toListEntity() = map( MovieResponse::toEntity)
}