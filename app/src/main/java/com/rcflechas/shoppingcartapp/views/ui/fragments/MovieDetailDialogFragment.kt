package com.rcflechas.shoppingcartapp.views.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.request.RequestOptions
import com.rcflechas.shoppingcartapp.R
import com.rcflechas.shoppingcartapp.core.onClick
import com.rcflechas.shoppingcartapp.core.setImageByUrl
import com.rcflechas.shoppingcartapp.models.data.remote.rest.TheMovieDB
import com.rcflechas.shoppingcartapp.viewmodels.MovieDetailViewModel
import com.rcflechas.shoppingcartapp.views.binds.CartBind
import com.rcflechas.shoppingcartapp.views.binds.MovieWithCartBind
import kotlinx.android.synthetic.main.cards_layout_amount.*
import kotlinx.android.synthetic.main.cards_layout_description.*
import kotlinx.android.synthetic.main.fragment_movie_detail_dialog.*
import kotlinx.android.synthetic.main.fragment_movie_detail_dialog.titleTextView
import kotlinx.android.synthetic.main.fragment_movie_detail_dialog.toolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailDialogFragment : DialogFragment() {

    private val movieDetailViewModel: MovieDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar(view)
        arguments?.getSerializable("movieWithCartBind")?.let {
            initUI(it as MovieWithCartBind)
        }
    }

    private fun setupToolbar(view: View) {
        toolbar.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close_24)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationOnClickListener {
            dismiss()
        }
    }

    private fun initUI(movieWithCartBind: MovieWithCartBind) {
        movieWithCartBind.run {

            movieImageView.setImageByUrl(
                url = "${TheMovieDB.URL_IMAGE_W780}${movie.backdropPath}",
                options = RequestOptions().centerCrop()
            )

            titleTextView.text = movie.title
            descriptionTextView.text = movie.overView

            if (cart.amount > 0) {

                countTextView.text = cart.amount.toString()
                actionMaterialButton.text = getString(R.string.title_update)
                actionMaterialButton.isEnabled = false
            } else {

                cart.amount++
                countTextView.text = cart.amount.toString()
                actionMaterialButton.text = getString(R.string.title_add)
                actionMaterialButton.isEnabled = true
            }

            addImageButton.onClick {

                cart.amount++
                countTextView.text = cart.amount.toString()
                actionMaterialButton.isEnabled = true
            }

            removeImageButton.onClick {

                when {
                    (cart.id == 0 && cart.amount > 1) -> {

                        movieWithCartBind.cart.amount--
                        countTextView.text = movieWithCartBind.cart.amount.toString()
                        actionMaterialButton.isEnabled = true
                    }
                    (cart.id > 0 && cart.amount > 0) -> {

                        movieWithCartBind.cart.amount--
                        countTextView.text = movieWithCartBind.cart.amount.toString()
                        actionMaterialButton.isEnabled = true
                    }
                }
            }

            actionMaterialButton.onClick {

                when {

                    (cart.id == 0 && cart.amount > 0) -> {
                        movieDetailViewModel.insertCartLocal(CartBind(id = cart.id, movieId = movie.id, amount = cart.amount))
                    }

                    (cart.id > 0 && cart.amount > 0) -> {
                        movieDetailViewModel.updateCartLocal(CartBind(id = cart.id, movieId = movie.id, amount = cart.amount))
                    }

                    (cart.id > 0 && cart.amount == 0) -> {
                        movieDetailViewModel.deleteCartLocal(CartBind(id = cart.id, movieId = movie.id, amount = cart.amount))
                    }
                }
                dismiss()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    companion object {
        const val TAG = "MovieDetailDialogFragment"

        fun newInstance(bundle: Bundle? = null): MovieDetailDialogFragment = MovieDetailDialogFragment().apply { arguments = bundle }
    }
}