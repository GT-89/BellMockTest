package com.example.belltakehome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.belltakehome.databinding.ItemMoviePosterBinding
import com.example.belltakehome.models.Movie

class MoviePosterAdapter(val movieList: List<Movie>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MoviePosterViewHolder(
            ItemMoviePosterBinding
                .inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = (holder as MoviePosterViewHolder).bind(movieList[position])

    override fun getItemCount(): Int = movieList.size
}