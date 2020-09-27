package com.rcflechas.shoppingcartapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rcflechas.shoppingcartapp.models.data.local.entities.CartWithMovie
import com.rcflechas.shoppingcartapp.models.data.local.entities.Movie
import com.rcflechas.shoppingcartapp.models.repositories.CartRepository
import com.rcflechas.shoppingcartapp.models.repositories.MovieRepository
import com.rcflechas.shoppingcartapp.utilities.Event
import com.rcflechas.shoppingcartapp.utilities.UIState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CartViewModel (private val cartRepository: CartRepository) : ViewModel() {

    private val cartWithMovieListMutableLiveData: MutableLiveData<Event<UIState>> = MutableLiveData()


    fun getCartWithMovieListLiveData(): LiveData<Event<UIState>> = cartWithMovieListMutableLiveData

    private val subscriptions = CompositeDisposable()

    fun getCartWithMovieLocal() {
        subscriptions.add(
            cartRepository.getCartWithMovieLocal()
                .doOnSubscribe {
                    cartWithMovieListMutableLiveData.postValue(Event(UIState.Loading))
                }.subscribeOn(Schedulers.io())
                .subscribeBy(
                    onNext = {
                        cartWithMovieListMutableLiveData.postValue(Event(UIState.Success(CartWithMovie.mapperCartWithMovieToCartWithMovieBindList(it))))
                    },
                    onError = {
                        cartWithMovieListMutableLiveData.postValue(
                            Event(
                                UIState.Error(
                                    it.message
                                        ?: "Error"
                                )
                            )
                        )
                    }
                )
        )
    }
}