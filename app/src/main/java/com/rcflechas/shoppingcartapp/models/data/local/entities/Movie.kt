package com.rcflechas.shoppingcartapp.models.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rcflechas.shoppingcartapp.models.data.remote.responses.MovieResponse
import com.rcflechas.shoppingcartapp.views.binds.MovieBind
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(tableName = "movie")
data class Movie(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "movi_id")
    @field:Json(name = "movi_id")
    val id: Int = 0,

    @ColumnInfo(name = "movi_title")
    @field:Json(name = "movi_title")
    val title: String = String(),

    @ColumnInfo(name = "movi_overview")
    @field:Json(name = "movi_overview")
    val overView: String,

    @ColumnInfo(name = "movi_posterpath")
    @field:Json(name = "movi_posterpath")
    val posterPath: String = String(),

    @ColumnInfo(name = "movi_backdroppath")
    @field:Json(name = "movi_backdroppath")
    val backdropPath: String = String(),

    @ColumnInfo(name = "movi_isadult")
    @field:Json(name = "movi_isadult")
    val isAdult: Boolean = false
): Serializable {

    companion object {

        fun mapperMovieToMovieBindList(movies: List<Movie>): List<MovieBind> {

            val list = mutableListOf<MovieBind>()
            movies.forEach {
                list.add(
                    MovieBind(
                        id = it.id,
                        title = it.title,
                        overView = it.overView,
                        posterPath = it.posterPath,
                        backdropPath = it.backdropPath,
                        isAdult = it.isAdult
                    )
                )
            }
            return list.toList()
        }
    }
}