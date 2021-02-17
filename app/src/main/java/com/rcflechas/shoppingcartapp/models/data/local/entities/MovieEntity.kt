package com.rcflechas.shoppingcartapp.models.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rcflechas.shoppingcartapp.views.binds.MovieBind
import com.rcflechas.shoppingcartapp.views.mappers.BindMapper
import java.io.Serializable

@Entity(tableName = "movieEntity")
data class MovieEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "movi_id")
    val id: Int = 0,

    @ColumnInfo(name = "movi_title")
    val title: String = String(),

    @ColumnInfo(name = "movi_overview")
    val overView: String,

    @ColumnInfo(name = "movi_posterpath")
    val posterPath: String = String(),

    @ColumnInfo(name = "movi_backdroppath")
    val backdropPath: String = String(),

    @ColumnInfo(name = "movi_isadult")
    val isAdult: Boolean = false

): Serializable, BindMapper<MovieEntity, MovieBind> {

    override fun toBind() = MovieBind(
        id = id,
        title = title,
        overView = overView,
        posterPath = posterPath,
        backdropPath = backdropPath,
        isAdult = isAdult
    )

    override fun List<MovieEntity>.toListBind() = map( MovieEntity::toBind )
}