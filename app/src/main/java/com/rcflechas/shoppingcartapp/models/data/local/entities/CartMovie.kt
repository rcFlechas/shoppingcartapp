package com.rcflechas.shoppingcartapp.models.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "cart_movie")
data class CartMovie (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "camo_id")
    @field:Json(name = "camo_id")
    val id: Int = 0,

    @ColumnInfo(name = "cart_id")
    @field:Json(name = "cart_id")
    val cartId: Int = 0,

    @ColumnInfo(name = "movi_id")
    @field:Json(name = "movi_id")
    val movieId: Int = 0,

    @ColumnInfo(name = "camo_amount")
    @field:Json(name = "camo_amount")
    val amount: Int = 0
)