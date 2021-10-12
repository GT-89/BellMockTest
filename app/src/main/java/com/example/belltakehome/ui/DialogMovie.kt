package com.example.belltakehome.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.example.belltakehome.databinding.DialogMovieBinding
import com.example.belltakehome.models.Movie

class DialogMovie(private val movie: Movie): DialogFragment() {

    private var _binding: DialogMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvMovieTitle.text = movie.title
            tvSummary.text = movie.summary
            Glide.with(binding.root.context)
                .load(movie.imageUrl)
                .into(binding.ivMovie)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        dialog?.let { dialog ->
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    fun show(fragment: FragmentManager) = show(fragment, "DialogMovieFragment")
}