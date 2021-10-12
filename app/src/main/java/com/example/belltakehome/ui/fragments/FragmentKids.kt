package com.example.belltakehome.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.belltakehome.databinding.FragmentKidsBinding
import com.example.belltakehome.models.Movie
import toothpick.InjectConstructor


/**
 * Fragment Class responsible for connecting views
 * to the appropriate live data pertaining to kids Movie posters
 */
@InjectConstructor
class FragmentKids: BaseFragment() {

    private var _binding: FragmentKidsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentKidsBinding.inflate(inflater, container, false)
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

    private fun notifyViewmodelScreen() {
        viewModel.apply {
            setScreen("Kids")
            Log.i("STATUSCHECK", "Kids")
//            retrieveScreenDetails()
        }
    }

    private fun populateRecyclerView(movieListLiveData: List<Movie>?) {
        movieListLiveData?.let {
//            binding.rvMovies.apply {
//                adapter = MoviePosterAdapter(it)
//                layoutManager = GridLayoutManager(requireContext(), 3)
//                setHasFixedSize(true)
//            }
        }
    }
}