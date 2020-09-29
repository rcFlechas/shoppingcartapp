package com.rcflechas.shoppingcartapp.models.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rcflechas.shoppingcartapp.views.binds.CartBind
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(tableName = "cart")
data class Cart(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cart_id")
    val id: Int = 0,

    @ColumnInfo(name = "movi_id")
    val movieId: Int = 0,

    @ColumnInfo(name = "cart_amount")
    val amount: Int = 0
): Serializable {
    companion object{

        fun mapperCartToCartBind(cart: Cart) = CartBind(
            id = cart.id,
            movieId = cart.movieId,
            amount = cart.amount
        )

        fun mapperCartToCartBindList(carts: List<Cart>): List<CartBind> {

            val list = mutableListOf<CartBind>()
            carts.forEach {
                list.add(
                    CartBind(
                        id = it.id,
                        movieId = it.movieId,
                        amount = it.amount
                    )
                )
            }
            return list.toList()
        }
    }
}