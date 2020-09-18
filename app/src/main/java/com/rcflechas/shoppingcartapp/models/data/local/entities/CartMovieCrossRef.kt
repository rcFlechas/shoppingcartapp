package com.rcflechas.shoppingcartapp.models.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(primaryKeys = ["cart_id", "camo_id"])
data class CartMovieCrossRef (

    @ColumnInfo(name = "cart_id")
    @field:Json(name = "cart_id")
    val cartId: Int = 0,

    @ColumnInfo(name = "camo_id")
    @field:Json(name = "camo_id")
    val movieId: Int = 0

): Serializable