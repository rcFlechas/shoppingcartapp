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
import com.rcflechas.shoppingcartapp.core.setImageByUrl
import com.rcflechas.shoppingcartapp.models.data.remote.rest.TheMovieDB
import com.rcflechas.shoppingcartapp.views.binds.MovieBind
import kotlinx.android.synthetic.main.cards_layout_description.*
import kotlinx.android.synthetic.main.fragment_movie_detail_dialog.*
import kotlinx.android.synthetic.main.fragment_movie_detail_dialog.titleTextView
import kotlinx.android.synthetic.main.fragment_movie_detail_dialog.toolbar

class MovieDetailDialogFragment : DialogFragment() {


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
        arguments?.getSerializable("movie")?.let {
            initUI(it as MovieBind)
        }
    }

    private fun setupToolbar(view: View) {
        toolbar.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close_24)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationOnClickListener {
            dismiss()
        }
    }

    private fun initUI(movie: MovieBind) {
        movie.apply {

            movieImageView.setImageByUrl(
                url = "${TheMovieDB.URL_IMAGE_W780}${movie.backdropPath}",
                options = RequestOptions().centerCrop()
            )

            titleTextView.text = title
            descriptionTextView.text = overView
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