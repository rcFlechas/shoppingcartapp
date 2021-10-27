package com.rcflechas.shoppingcartapp.views.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.request.RequestOptions
import com.rcflechas.shoppingcartapp.R
import com.rcflechas.shoppingcartapp.core.onClick
import com.rcflechas.shoppingcartapp.core.setImageByUrl
import com.rcflechas.shoppingcartapp.databinding.FragmentMovieDetailDialogBinding
import com.rcflechas.shoppingcartapp.models.data.remote.rest.TheMovieDB
import com.rcflechas.shoppingcartapp.viewmodels.MovieDetailViewModel
import com.rcflechas.shoppingcartapp.views.binds.CartBind
import com.rcflechas.shoppingcartapp.views.binds.MovieWithCartBind
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailDialogFragment : DialogFragment() {

    private var _binding: FragmentMovieDetailDialogBinding? = null
    private val binding get() = _binding

    private val movieDetailViewModel: MovieDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): CoordinatorLayout? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDetailDialogBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar(view)
        arguments?.getSerializable("movieWithCartBind")?.let {
            initUI(it as MovieWithCartBind)
        }
    }

    private fun setupToolbar(view: View) {
        binding?.toolbar?.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close_24)
        binding?.toolbar?.setTitleTextColor(Color.WHITE)
        binding?.toolbar?.setNavigationOnClickListener {
            dismiss()
        }
    }

    private fun initUI(movieWithCartBind: MovieWithCartBind) {
        movieWithCartBind.run {

            binding?.movieImageView?.setImageByUrl(
                url = "${TheMovieDB.URL_IMAGE_W780}${movie.backdropPath}",
                options = RequestOptions().centerCrop()
            )

            binding?.titleTextView?.text = movie.title
            binding?.includeCardsDescription?.descriptionTextView?.text = movie.overView

            if (cart.amount > 0) {

                binding?.includeCardsAmount?.countTextView?.text = cart.amount.toString()
                binding?.includeCardsAmount?.actionMaterialButton?.text = getString(R.string.title_update)
                binding?.includeCardsAmount?.actionMaterialButton?.isEnabled = false
            } else {

                cart.amount++
                binding?.includeCardsAmount?.countTextView?.text = cart.amount.toString()
                binding?.includeCardsAmount?.actionMaterialButton?.text = getString(R.string.title_add)
                binding?.includeCardsAmount?.actionMaterialButton?.isEnabled = true
            }

            binding?.includeCardsAmount?.addImageButton?.onClick {

                cart.amount++
                binding?.includeCardsAmount?.countTextView?.text = cart.amount.toString()
                binding?.includeCardsAmount?.actionMaterialButton?.isEnabled = true
            }

            binding?.includeCardsAmount?.removeImageButton?.onClick {

                when {
                    (cart.id == 0 && cart.amount > 1) -> {

                        movieWithCartBind.cart.amount--
                        binding?.includeCardsAmount?.countTextView?.text = movieWithCartBind.cart.amount.toString()
                        binding?.includeCardsAmount?.actionMaterialButton?.isEnabled = true
                    }
                    (cart.id > 0 && cart.amount > 0) -> {

                        movieWithCartBind.cart.amount--
                        binding?.includeCardsAmount?.countTextView?.text = movieWithCartBind.cart.amount.toString()
                        binding?.includeCardsAmount?.actionMaterialButton?.isEnabled = true
                    }
                }
            }

            binding?.includeCardsAmount?.actionMaterialButton?.onClick {

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}