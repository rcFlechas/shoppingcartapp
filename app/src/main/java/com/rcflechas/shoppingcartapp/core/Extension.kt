package com.rcflechas.shoppingcartapp.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.rcflechas.shoppingcartapp.R
import com.rcflechas.shoppingcartapp.models.data.remote.rest.TheMovieDB
import com.rcflechas.shoppingcartapp.views.widget.GlideApp
import kotlinx.android.synthetic.main.item_movie.view.*

internal infix fun View.onClick(function: () -> Unit) {
    setOnClickListener { function() }
}

internal fun Context.isConnect(): Boolean {

    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network      = connectivityManager.activeNetwork ?: return false
    val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

    return when {
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
        else -> false
    }
}

internal fun ImageView.setImageByUrl(url: String, options: RequestOptions = RequestOptions()) {

    GlideApp.with(this.context)
        .load(url)
        .placeholder(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_background)
        .apply(options)
        .into(this)
}