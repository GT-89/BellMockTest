package com.example.belltakehome

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.belltakehome.databinding.ItemMoviePosterBinding
import com.example.belltakehome.models.Movie

class MoviePosterViewHolder(val binding: ItemMoviePosterBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie){
        binding.apply {
            movie.title?.let { binding.tvMovieTitle.text = it }
            Glide.with(binding.root.context)
                .load(movie.imageUrl)
                .into(binding.ivMovie)
        }
    }

}