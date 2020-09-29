package com.rcflechas.shoppingcartapp.views.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rcflechas.shoppingcartapp.R
import com.rcflechas.shoppingcartapp.core.onClick
import com.rcflechas.shoppingcartapp.utilities.UIState
import com.rcflechas.shoppingcartapp.viewmodels.CartViewModel
import com.rcflechas.shoppingcartapp.views.adapters.MovieAdapter
import com.rcflechas.shoppingcartapp.views.binds.CartBind
import com.rcflechas.shoppingcartapp.views.binds.MovieWithCartBind
import com.rcflechas.shoppingcartapp.views.widget.SwipeToDeleteCallback
import kotlinx.android.synthetic.main.cards_layout_clear.*
import kotlinx.android.synthetic.main.empty_view.*
import kotlinx.android.synthetic.main.fragment_cart.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment : Fragment() {

    private val cartViewModel: CartViewModel by viewModel()
    private lateinit var  movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupHandler()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        setupToolbar()
    }

    override fun onResume() {
        super.onResume()
        cartViewModel.getCartWithMovieLocal()
    }

    private fun initUI() {

        setupRecyclerView()
        setupSwipe()

        clearMaterialButton.onClick {
            cartViewModel.deleteCartAllLocal()
        }
    }

    private fun setupToolbar() {

        toolbar.navigationIcon = view?.context?.let { ContextCompat.getDrawable(it, R.drawable.ic_back_24) }
        toolbar.setTitle(R.string.title_cart)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

    }

    private fun setupRecyclerView() {

        movieAdapter = MovieAdapter(clickClosure = {

            val bundle = bundleOf("movieWithCartBind" to it)
            findNavController().navigate(R.id.action_cartFragment_to_movieDetailFragmentDialog, bundle)
        }, addClosure = {

            if (it.cart.id == 0) {
                cartViewModel.insertCartLocal(CartBind(id = it.cart.id, movieId = it.movie.id, amount = it.cart.amount))
            } else {
                cartViewModel.updateCartLocal(CartBind(id = it.cart.id, movieId = it.movie.id, amount = it.cart.amount))
            }
        }, removeClosure = {

            if (it.cart.amount == 0) {
                cartViewModel.deleteCartLocal(CartBind(id = it.cart.id, movieId = it.movie.id, amount = it.cart.amount))
            } else {
                cartViewModel.updateCartLocal(CartBind(id = it.cart.id, movieId = it.movie.id, amount = it.cart.amount))
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

    private fun setupSwipe() {

        val swipeHandler = object : SwipeToDeleteCallback(requireActivity()) {

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val position = viewHolder.adapterPosition
                val deletedItem = movieAdapter.getItem(position)
                val data = movieAdapter.getData()
                data.removeAt(position)
                movieAdapter.animateTo(data)
                cartViewModel.deleteCartLocal(CartBind(id = deletedItem.cart.id, movieId = deletedItem.movie.id, amount = deletedItem.cart.amount))

                // showing snack bar with Undo option
                val snackbar = Snackbar.make(requireView(), "Item removed from list!", Snackbar.LENGTH_LONG)
                snackbar.setAction("UNDO") {

                    cartViewModel.insertCartLocal(CartBind(id = deletedItem.cart.id, movieId = deletedItem.movie.id, amount = deletedItem.cart.amount))
                    data.add(deletedItem)
                    movieAdapter.animateTo(data)
                    if (position == 0) {

                        moviesRecyclerView?.let {
                            val layoutManager = it.layoutManager as LinearLayoutManager
                            layoutManager.scrollToPosition(0)
                        }
                    }
                }
                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(moviesRecyclerView)
    }

    private fun dataEmpty(text: String = String()) {
        nestedScrollView.visibility = View.GONE
        includeEmptyView.visibility = View.VISIBLE
        loadingTextView.text = text
    }

    private fun dataNoEmpty() {
        nestedScrollView.visibility = View.VISIBLE
        includeEmptyView.visibility = View.GONE
    }

    private fun setupHandler() {

        cartViewModel.getCartWithMovieListLiveData().observe(this, { event ->

            event.getContentIfNotHandled()?.let { status ->

                when (status) {

                    is UIState.Loading -> {

                        Log.i(MovieFragment.TAG, "--- Loading...")
                    }
                    is UIState.Success<*> -> {

                        val data = status.data as List<MovieWithCartBind>
                        movieAdapter.clearData()
                        if (data.count() != 0) {

                            dataNoEmpty()
                            movieAdapter.setData(data as MutableList<MovieWithCartBind>)
                        } else {

                            dataEmpty(getString(R.string.message_list_empty))
                        }
                        Log.i(TAG, "--- Success...")
                    }
                    is UIState.Error -> {

                        includeEmptyView.visibility = View.VISIBLE
                        loadingTextView.text = getString(R.string.message_connection_error)
                        Log.i(TAG, "--- ${status.message}")
                    }
                }
            }
        })

        cartViewModel.insertCartLiveData().observe(this, { status ->
            when (status) {
                is UIState.Loading -> {
                    Log.i(MovieFragment.TAG, "--- Loading...")
                }
                is UIState.Success<*> -> {
                    val add = status.data as Boolean
                    cartViewModel.getCartWithMovieLocal()
                    Log.i(MovieFragment.TAG, "--- Success Insert")
                }
                is UIState.Error -> {
                    Log.i(MovieFragment.TAG, "--- ${status.message}")
                }
            }
        })

        cartViewModel.updateCartLiveData().observe(this, { status ->
            when (status) {
                is UIState.Loading -> {
                    Log.i(MovieFragment.TAG, "--- Loading...")
                }
                is UIState.Success<*> -> {
                    val add = status.data as Boolean
                    cartViewModel.getCartWithMovieLocal()
                    Log.i(MovieFragment.TAG, "--- Success Update")
                }
                is UIState.Error -> {
                    Log.i(MovieFragment.TAG, "--- ${status.message}")
                }
            }
        })

        cartViewModel.deleteCartLiveData().observe(this, { status ->
            when (status) {
                is UIState.Loading -> {
                    Log.i(MovieFragment.TAG, "--- Loading...")
                }
                is UIState.Success<*> -> {
                    val add = status.data as Boolean
                    cartViewModel.getCartWithMovieLocal()
                    Log.i(MovieFragment.TAG, "--- Success Delete")
                }
                is UIState.Error -> {
                    Log.i(MovieFragment.TAG, "--- ${status.message}")
                }
            }
        })

        cartViewModel.deleteCartAllLiveData().observe(this, { status ->
            when (status) {
                is UIState.Loading -> {
                    Log.i(MovieFragment.TAG, "--- Loading...")
                }
                is UIState.Success<*> -> {

                    val isDeleted = status.data as Boolean
                    if (isDeleted) {
                        movieAdapter.clearData()
                        cartViewModel.getCartWithMovieLocal()
                        Log.i(MovieFragment.TAG, "--- Success Delete All")
                    }
                }
                is UIState.Error -> {
                    Log.i(MovieFragment.TAG, "--- ${status.message}")
                }
            }
        })
    }

    companion object {
        const val TAG = "CartFragment"

        fun newInstance(bundle: Bundle? = null): CartFragment = CartFragment().apply { arguments = bundle }
    }
}