package com.example.belltakehome.toothpick

import android.app.Application
import android.content.Context
import toothpick.config.Module
import toothpick.ktp.binding.bind

class ApplicationModule(application: Application): Module() {

    init {
        bind<Context>().toInstance(application.applicationContext)
//        bind<APIClient>().toInstance(APIClient)
    }

}