package com.rcflechas.shoppingcartapp.models.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(tableName = "cart")
data class Cart(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cart_id")
    @field:Json(name = "cart_id")
    val id: Int = 0,

    @ColumnInfo(name = "cart_amount")
    @field:Json(name = "cart_amount")
    val amount: Int = 0
): Serializable