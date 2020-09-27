package com.rcflechas.shoppingcartapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rcflechas.shoppingcartapp.models.data.local.entities.Movie
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


    private val movieListMutableLiveData: MutableLiveData<Event<UIState>> = MutableLiveData()
    private val addCartMutableLiveData: MutableLiveData<UIState> = MutableLiveData()


    fun getMovieListLiveData(): LiveData<Event<UIState>> = movieListMutableLiveData
    fun addCartLiveData(): LiveData<UIState> = addCartMutableLiveData

    private val subscriptions = CompositeDisposable()


    fun getAllRemote() {

        subscriptions.add(
            movieRepository.getAllRemote()
                .doOnSubscribe {
                    movieListMutableLiveData.postValue(Event(UIState.Loading))
                }
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = {
                        movieRepository.insertAllLocal(MovieResponse.mapperMovieResponseToMovieEntityList(it.results))
                        movieListMutableLiveData.postValue(Event(UIState.Success(MovieResponse.mapperMovieResponseToMovieBindList(it.results))))
                    },
                    onError = {
                        movieListMutableLiveData.postValue(
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

    fun getAllLocal() {

        subscriptions.add(
            movieRepository.getAllLocal()
                .doOnSubscribe {
                    movieListMutableLiveData.postValue(Event(UIState.Loading))
                }.subscribeOn(Schedulers.io())
                .subscribeBy(
                    onNext = {
                        movieListMutableLiveData.postValue(Event(UIState.Success(Movie.mapperMovieToMovieBindList(it))))
                    },
                    onError = {
                        movieListMutableLiveData.postValue(
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

    fun insetCartLocal(cart: CartBind) {
        subscriptions.add(

            cartRepository.insertLocal(CartBind.mapperCartBindToCartEntity(cart)).doOnSubscribe {
                addCartMutableLiveData.postValue(
                    UIState.Loading
                )
            }
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onComplete = {
                    addCartMutableLiveData.postValue(UIState.Success(true))
                },
                onError = {
                    addCartMutableLiveData.postValue(
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