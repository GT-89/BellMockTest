package com.example.belltakehome.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.belltakehome.databinding.FragmentTvShowsBinding


/**
 * Fragment Class responsible for connecting views
 * to the appropriate live data pertaining to TV Show posters
 */
class FragmentTVShows: BaseFragment() {

    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notifyViewmodelScreen()
        setupObservers()
    }

    private fun setupObservers() {

    }

    private fun notifyViewmodelScreen() {
        viewModel.apply {
            setScreen("Kids")
            Log.i("STATUSCHECK", "TV shows")
//            retrieveScreenDetails()
        }
    }

}