package com.example.belltakehome.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.belltakehome.ui.adapters.MoviePosterAdapter
import com.example.belltakehome.databinding.FragmentHomeBinding
import com.example.belltakehome.models.Movie
import toothpick.InjectConstructor


/**
 * Fragment Class responsible for connecting views
 * to the appropriate live data pertaining to all Movie posters
 */
@InjectConstructor
class FragmentHome: BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notifyViewmodelScreen()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.moviesList.observe(viewLifecycleOwner) { movieListLiveData ->
            movieListLiveData?.let { populateRecyclerView(it) }
        }
    }

    private fun populateRecyclerView(movieListLiveData: List<Movie>?) {
        movieListLiveData?.let {
            binding.rvMovies.apply {
                adapter = MoviePosterAdapter(it, childFragmentManager)
                layoutManager = GridLayoutManager(requireContext(), 3)
                setHasFixedSize(true)
            }
        }
    }


    fun updateSearchResults(query: String) {
        //there isnt much to search since all the dummy
        // data is the same. the implementation would be here
    }

    private fun notifyViewmodelScreen() {
        viewModel.apply {
            setScreen("Kids")
            Log.i("STATUSCHECK", "Home")
            retrieveScreenDetails()
        }
    }
}