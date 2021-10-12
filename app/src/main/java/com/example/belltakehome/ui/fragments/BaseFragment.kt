package com.example.belltakehome.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.belltakehome.toothpick.ActivityScope
import com.example.belltakehome.toothpick.ApplicationScope
import com.example.belltakehome.toothpick.ViewModelScope
import com.example.belltakehome.ui.BellViewModel
import toothpick.ktp.KTP
import toothpick.ktp.delegate.inject
import toothpick.smoothie.viewmodel.closeOnViewModelCleared
import toothpick.smoothie.viewmodel.installViewModelBinding

abstract class BaseFragment: Fragment() {

    val viewModel: BellViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeToothpick()
        setHasOptionsMenu(true)
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
}