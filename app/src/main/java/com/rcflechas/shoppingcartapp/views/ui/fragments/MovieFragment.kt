package com.rcflechas.shoppingcartapp.views.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rcflechas.shoppingcartapp.R
import com.rcflechas.shoppingcartapp.core.isConnect
import com.rcflechas.shoppingcartapp.core.setBadge
import com.rcflechas.shoppingcartapp.models.data.local.entities.CartWithMovie
import com.rcflechas.shoppingcartapp.utilities.UIState
import com.rcflechas.shoppingcartapp.viewmodels.MovieViewModel
import com.rcflechas.shoppingcartapp.views.adapters.MovieAdapter
import com.rcflechas.shoppingcartapp.views.binds.CartBind
import com.rcflechas.shoppingcartapp.views.binds.MovieBind
import com.rcflechas.shoppingcartapp.views.binds.MovieWithCartBind
import kotlinx.android.synthetic.main.empty_view.*
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var  movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupHandler()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        setupToolbar()
        //movieViewModel.getAllRemote()
    }

    override fun onResume() {
        super.onResume()

        val isConnect = context?.isConnect() ?: false
        if (isConnect) {
            movieViewModel.getAllRemote()
        }

        movieViewModel.getAllCartWithMovieLocal()
    }

    private fun initUI() {

        movieAdapter = MovieAdapter ( clickClosure = {

            val bundle = bundleOf("movie" to it.movie)
            findNavController().navigate(R.id.action_movieFragment_to_movieDetailFragmentDialog, bundle)
        }, addClosure = {

            if (it.cart.id == 0) {
                movieViewModel.insertCartLocal(CartBind(id = it.cart.id, movieId = it.movie.id, amount = it.cart.amount))
            } else {
                movieViewModel.updateCartLocal(CartBind(id = it.cart.id, movieId = it.movie.id, amount = it.cart.amount))
            }
        }, removeClosure = {

            if (it.cart.amount == 0) {
                movieViewModel.deleteCartLocal(CartBind(id = it.cart.id, movieId = it.movie.id, amount = it.cart.amount))
            } else {
                movieViewModel.updateCartLocal(CartBind(id = it.cart.id, movieId = it.movie.id, amount = it.cart.amount))
            }
        })

        movieAdapter.setHasStableIds(true)
        moviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun setupToolbar() {
        toolbar.setTitle(R.string.app_name)
        toolbar.inflateMenu(R.menu.menu)

        toolbar.menu[0].setBadge(
            context = requireContext(),
            count = 2,
            image = R.drawable.ic_cart_32
        )

        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_main_cart -> {
                    findNavController().navigate(R.id.action_movieFragment_to_cartFragment)
                }
            }
            true
        }
    }

    private fun setupHandler() {

        movieViewModel.getCartWithMovieListLiveData().observe(this, { event ->

            event.getContentIfNotHandled()?.let { status ->

                when (status) {

                    is UIState.Loading -> {

                        Log.i(TAG, "--- Loading...")
                    }
                    is UIState.Success<*> -> {

                        val data = status.data as List<MovieWithCartBind>
                        Log.i(TAG, "--- Success...")
                        if (data.count() != 0) {


                            includeEmptyView.visibility = View.GONE
                            movieAdapter.setData(data as MutableList<MovieWithCartBind>)
                        } else {

                            includeEmptyView.visibility = View.VISIBLE
                            loadingTextView.text = getString(R.string.message_list_empty)
                        }
                    }
                    is UIState.Error -> {

                        includeEmptyView.visibility = View.VISIBLE
                        loadingTextView.text = getString(R.string.message_connection_error)
                        Log.i(TAG, "--- ${status.message}")
                    }
                }
            }
        })

        movieViewModel.insertCartLiveData().observe(this, { status ->
            when (status) {
                is UIState.Loading -> {
                    Log.i(TAG, "--- Loading...")
                }
                is UIState.Success<*> -> {
                    val add = status.data as Boolean
                    movieViewModel.getAllCartWithMovieLocal()
                    Log.i(TAG, "--- Success Insert")
                }
                is UIState.Error -> {
                    Log.i(TAG, "--- ${status.message}")
                }
            }
        })

        movieViewModel.updateCartLiveData().observe(this, { status ->
            when (status) {
                is UIState.Loading -> {
                    Log.i(TAG, "--- Loading...")
                }
                is UIState.Success<*> -> {
                    val add = status.data as Boolean
                    movieViewModel.getAllCartWithMovieLocal()
                    Log.i(TAG, "--- Success Update")
                }
                is UIState.Error -> {
                    Log.i(TAG, "--- ${status.message}")
                }
            }
        })

        movieViewModel.deleteCartLiveData().observe(this, { status ->
            when (status) {
                is UIState.Loading -> {
                    Log.i(TAG, "--- Loading...")
                }
                is UIState.Success<*> -> {
                    val add = status.data as Boolean
                    movieViewModel.getAllCartWithMovieLocal()
                    Log.i(TAG, "--- Success Delete")
                }
                is UIState.Error -> {
                    Log.i(TAG, "--- ${status.message}")
                }
            }
        })
    }

    companion object {
        const val TAG = "MovieFragment"

        fun newInstance(bundle: Bundle? = null): MovieFragment = MovieFragment().apply { arguments = bundle }
    }
}