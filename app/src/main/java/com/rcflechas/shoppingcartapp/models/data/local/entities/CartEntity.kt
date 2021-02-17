package com.rcflechas.shoppingcartapp.models.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rcflechas.shoppingcartapp.views.binds.CartBind
import com.rcflechas.shoppingcartapp.views.mappers.BindMapper
import java.io.Serializable

@Entity(tableName = "cartEntity")
data class CartEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cart_id")
    val id: Int = 0,

    @ColumnInfo(name = "movi_id")
    val movieId: Int = 0,

    @ColumnInfo(name = "cart_amount")
    val amount: Int = 0

): Serializable, BindMapper<CartEntity, CartBind> {

    override fun toBind() = CartBind(
        id = id,
        movieId = movieId,
        amount = amount
    )

    override fun List<CartEntity>.toListBind() = map( CartEntity::toBind )
}