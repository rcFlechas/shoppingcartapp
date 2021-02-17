package com.rcflechas.shoppingcartapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rcflechas.shoppingcartapp.models.data.local.entities.MovieWithCartEntity
import com.rcflechas.shoppingcartapp.models.data.remote.responses.MovieResponse
import com.rcflechas.shoppingcartapp.models.repositories.CartRepository
import com.rcflechas.shoppingcartapp.models.repositories.MovieRepository
import com.rcflechas.shoppingcartapp.utilities.Event
import com.rcflechas.shoppingcartapp.utilities.UIState
import com.rcflechas.shoppingcartapp.views.binds.CartBind
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MovieViewModel (private val movieRepository: MovieRepository, private val cartRepository: CartRepository) : ViewModel() {


    private val cartWithMovieListMutableLiveData: MutableLiveData<Event<UIState>> = MutableLiveData()
    private val insertCartMutableLiveData: MutableLiveData<UIState> = MutableLiveData()
    private val updateCartMutableLiveData: MutableLiveData<UIState> = MutableLiveData()
    private val deleteCartMutableLiveData: MutableLiveData<UIState> = MutableLiveData()


    fun getCartWithMovieListLiveData(): LiveData<Event<UIState>> = cartWithMovieListMutableLiveData
    fun insertCartLiveData(): LiveData<UIState> = insertCartMutableLiveData
    fun updateCartLiveData(): LiveData<UIState> = updateCartMutableLiveData
    fun deleteCartLiveData(): LiveData<UIState> = deleteCartMutableLiveData

    private val subscriptions = CompositeDisposable()


    fun getAllRemote() {

        subscriptions.add(
            movieRepository.getAllRemote()
                .doOnSubscribe {

                }
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = {
                        movieRepository.insertAllLocal(it.results.map(MovieResponse::toEntity))
                    },
                    onError = {

                    }
                )
        )
    }

    fun getAllCartWithMovieLocal() {

        subscriptions.add(
            movieRepository.getMovieWithCartLocal()
                .doOnSubscribe {
                    cartWithMovieListMutableLiveData.postValue(Event(UIState.Loading))
                }.subscribeOn(Schedulers.io())
                .subscribeBy(
                    onNext = {
                        cartWithMovieListMutableLiveData.postValue(Event(UIState.Success(it.map(MovieWithCartEntity::toBind))))
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

    fun insertCartLocal(cart: CartBind) {
        subscriptions.add(

            cartRepository.insertLocal(cart.toEntity()).doOnSubscribe {
                insertCartMutableLiveData.postValue(
                    UIState.Loading
                )
            }
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onComplete = {
                    insertCartMutableLiveData.postValue(UIState.Success(true))
                },
                onError = {
                    insertCartMutableLiveData.postValue(
                        UIState.Error(
                            it.message ?: "Error"
                        )
                    )
                }
            )
        )
    }

    fun updateCartLocal(cart: CartBind) {
        subscriptions.add(

            cartRepository.insertLocal(cart.toEntity()).doOnSubscribe {
                updateCartMutableLiveData.postValue(
                    UIState.Loading
                )
            }
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onComplete = {
                        updateCartMutableLiveData.postValue(UIState.Success(true))
                    },
                    onError = {
                        updateCartMutableLiveData.postValue(
                            UIState.Error(
                                it.message ?: "Error"
                            )
                        )
                    }
                )
        )
    }

    fun deleteCartLocal(cart: CartBind) {
        subscriptions.add(

            cartRepository.deleteLocal(cart.toEntity()).doOnSubscribe {
                deleteCartMutableLiveData.postValue(
                    UIState.Loading
                )
            }
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onComplete = {
                        deleteCartMutableLiveData.postValue(UIState.Success(true))
                    },
                    onError = {
                        deleteCartMutableLiveData.postValue(
                            UIState.Error(
                                it.message ?: "Error"
                            )
                        )
                    }
                )
        )
    }

    companion object {
        private const val TAG = "MovieViewModel"
    }
}