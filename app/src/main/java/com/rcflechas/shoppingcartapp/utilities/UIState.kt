package com.rcflechas.shoppingcartapp.utilities

sealed class UIState {
    object Loading : UIState()
    class Success<T>(val data: T) : UIState()
    class Error(val message: String) : UIState()
}