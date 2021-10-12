package com.example.belltakehome.toothpick

import android.app.Activity
import com.example.belltakehome.network.BellMockApiProvider
import com.example.belltakehome.ui.BellViewModel
import toothpick.config.Module
import toothpick.ktp.binding.bind

class ActivityModule(activity: Activity): Module() {
    init {
        bind<BellMockApiProvider>().toClass<BellMockApiProvider>().singleton()
//        bind<BellViewModel>().toClass<BellViewModel>().singleton()
    }
}