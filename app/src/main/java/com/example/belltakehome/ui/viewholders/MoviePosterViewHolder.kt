package com.example.belltakehome.ui.viewholders

import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.belltakehome.databinding.ItemMoviePosterBinding
import com.example.belltakehome.models.Movie
import com.example.belltakehome.ui.DialogMovie


/**
 * ViewHolder class responsible for updating Movie Poster views
 *
 * @param binding binding variable for specified view
 */
class MoviePosterViewHolder(val binding: ItemMoviePosterBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie, childFragmentManager: FragmentManager){
        binding.apply {
            movie.title?.let { binding.tvMovieTitle.text = it }
            Glide.with(binding.root.context)
                .load(movie.imageUrl)
                .into(binding.ivMovie)
            llMovie.setOnClickListener {
                DialogMovie(movie)
                    .show(childFragmentManager)
            }
        }
    }

}