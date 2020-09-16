package com.rcflechas.shoppingcartapp.models.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(primaryKeys = ["movi_id", "cart_id"])
data class MovieCartCrossRef (

    @ColumnInfo(name = "movi_id")
    @field:Json(name = "movi_id")
    val movieId: Int = 0,

    @ColumnInfo(name = "cart_id")
    @field:Json(name = "cart_id")
    val cartId: Int = 0
): Serializable