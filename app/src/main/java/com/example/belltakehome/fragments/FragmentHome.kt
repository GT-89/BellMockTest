package com.example.belltakehome.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.belltakehome.MoviePosterAdapter
import com.example.belltakehome.databinding.FragmentHomeBinding
import com.example.belltakehome.models.BellViewModel
import com.example.belltakehome.models.Movie
import com.example.belltakehome.toothpick.ActivityScope
import com.example.belltakehome.toothpick.ApplicationScope
import com.example.belltakehome.toothpick.ViewModelScope
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject
import toothpick.smoothie.viewmodel.closeOnViewModelCleared
import toothpick.smoothie.viewmodel.installViewModelBinding

class FragmentHome: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BellViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeToothpick()
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun initializeToothpick() {
        KTP
            .openRootScope()
            .openSubScope(ApplicationScope::class.java)
            .openSubScope(ActivityScope::class.java)
            .openSubScope(ViewModelScope::class.java){
                it.installViewModelBinding<BellViewModel>(requireActivity())
                it.closeOnViewModelCleared(requireActivity())
            }
            .inject(this)
    }

    private fun setupObservers() {
        viewModel.moviesList.observe(viewLifecycleOwner) { movieListLiveData ->
            movieListLiveData?.let { populateRecyclerView(it) }
        }
    }

    private fun populateRecyclerView(movieListLiveData: List<Movie>?) {
        movieListLiveData?.let {
            binding.rvMovies.apply {
                adapter = MoviePosterAdapter(it)
                layoutManager = GridLayoutManager(requireContext(), 3)
                setHasFixedSize(true)
            }
        }
    }


    fun updateSearchResults(query: String) {
        //do something
    }
}