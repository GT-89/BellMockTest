package com.example.belltakehome.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.belltakehome.network.BellMockApiProvider
import com.example.belltakehome.models.Movie
import com.example.belltakehome.models.ScreenDetailsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import toothpick.ktp.delegate.lazy


/**
 * Base ViewModel  class
 */
class BellViewModel: ViewModel() {

    private val _screenDetailsResponse: MutableLiveData<ScreenDetailsResponse?> = MutableLiveData()
    val screenDetailsResponse: MutableLiveData<ScreenDetailsResponse?> = _screenDetailsResponse

    private val _moviesList: MutableLiveData<List<Movie>?> = MutableLiveData()
    val moviesList: MutableLiveData<List<Movie>?> = _moviesList

    private val bellMockApiProvider by lazy<BellMockApiProvider>()

    lateinit var screenTitle: String

    fun retrieveScreenDetails() {
        viewModelScope.launch(Dispatchers.Main) {
            bellMockApiProvider.apply {
                getListOfScreens()
                    ?.filter { it.title == screenTitle && it.screenType == "home" }
                    ?.let { screenResponseList ->
                        screenResponseList
                            .first()
                            .alias
                            ?.alias
                            ?.let { screenAlias ->
                                getScreenDetailsData(screenAlias)
                                    ?.let { screenDetailsResponse ->
                                        screenDetailsResponse
                                            .filter { it.style == "posters" }
                                            .first()
                                            .alias
                                            ?.alias
                                            ?.let { screenDetailsAlias ->
                                                getMoviePosters(screenDetailsAlias)
                                                    .let { moviesResponseList ->
                                                        moviesList.postValue(moviesResponseList)
                                                    }
                                            }
                                    }
                            }

                    }
            }

        }
    }

    fun setScreen(title: String) {
        screenTitle = title
    }
}