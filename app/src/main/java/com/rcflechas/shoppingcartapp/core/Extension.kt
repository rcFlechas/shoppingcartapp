package com.rcflechas.shoppingcartapp.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.request.RequestOptions
import com.rcflechas.shoppingcartapp.R
import com.rcflechas.shoppingcartapp.utilities.Utilities
import com.rcflechas.shoppingcartapp.views.widget.GlideApp

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

internal fun MenuItem.setBadge(context: Context, image: Int, count: Int) {
    this.icon = if (count > 0) {
        Utilities.convertLayoutToView(context, count, image)
    } else {
        ContextCompat.getDrawable(context, image)
    }
}