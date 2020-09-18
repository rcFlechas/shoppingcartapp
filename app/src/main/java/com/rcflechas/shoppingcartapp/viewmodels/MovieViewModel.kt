package com.rcflechas.shoppingcartapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rcflechas.shoppingcartapp.models.data.remote.responses.MovieResponse
import com.rcflechas.shoppingcartapp.models.repositories.CartRepository
import com.rcflechas.shoppingcartapp.models.repositories.MovieRepository
import com.rcflechas.shoppingcartapp.utilities.Event
import com.rcflechas.shoppingcartapp.utilities.UIState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MovieViewModel (private val movieRepository: MovieRepository, private val cartRepository: CartRepository) : ViewModel() {


    private val movieListRemoteMutableLiveData: MutableLiveData<Event<UIState>> = MutableLiveData()


    fun getMovieListRemoteLiveData(): LiveData<Event<UIState>> = movieListRemoteMutableLiveData

    private val subscriptions = CompositeDisposable()


    fun getAllRemote() {

        subscriptions.add(
            movieRepository.getAllRemote()
                .doOnSubscribe {
                    movieListRemoteMutableLiveData.postValue(Event(UIState.Loading))
                }
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = {
                        movieRepository.insertAllLocal(MovieResponse.mapperMovieResponseToMovieEntityList(it.results))
                        movieListRemoteMutableLiveData.postValue(Event(UIState.Success(MovieResponse.mapperMovieResponseToMovieBindList(it.results))))
                    },
                    onError = {
                        movieListRemoteMutableLiveData.postValue(
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

    companion object {
        private const val TAG = "MovieViewModel"
    }
}