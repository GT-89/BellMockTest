package com.example.belltakehome.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.belltakehome.databinding.ItemMoviePosterBinding
import com.example.belltakehome.models.Movie
import com.example.belltakehome.ui.viewholders.MoviePosterViewHolder


/**
 * Adapter class responsible for binding individual Movie parameters
 * to their appropriate fields in the recyclerview
 *
 * @param movieList list of movies to bind
 */
class MoviePosterAdapter(val movieList: List<Movie>,
                         val childFragmentManager: FragmentManager): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MoviePosterViewHolder(
            ItemMoviePosterBinding
                .inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as MoviePosterViewHolder).bind(movieList[position], childFragmentManager)

    override fun getItemCount(): Int = movieList.size
}