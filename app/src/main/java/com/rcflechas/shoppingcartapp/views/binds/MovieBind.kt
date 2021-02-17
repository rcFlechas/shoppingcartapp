package com.rcflechas.shoppingcartapp.views.binds

import com.rcflechas.shoppingcartapp.models.data.local.entities.MovieEntity
import com.rcflechas.shoppingcartapp.models.data.local.mappers.EntityMapper
import java.io.Serializable

data class MovieBind(
    
    val id: Int = 0,
    val title: String = String(),
    val overView: String,
    val posterPath: String = String(),
    val backdropPath: String = String(),
    val isAdult: Boolean = false

): Serializable, EntityMapper<MovieBind, MovieEntity> {

    override fun toEntity() = MovieEntity (
        id = id,
        title = title,
        overView = overView,
        posterPath = posterPath,
        backdropPath = backdropPath,
        isAdult = isAdult
    )

    override fun List<MovieBind>.toListEntity() = map( MovieBind::toEntity )
}