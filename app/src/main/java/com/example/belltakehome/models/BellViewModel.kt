package com.example.belltakehome.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.belltakehome.BellMockApiProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BellViewModel: ViewModel() {

    private val _moviesList: MutableLiveData<List<Movie>?> = MutableLiveData()
    val moviesList: MutableLiveData<List<Movie>?> = _moviesList

    init {
//        loadMovieList()
    }


    private fun loadMovieList() {
        viewModelScope.launch(Dispatchers.Main) {
            BellMockApiProvider().apply {
                getListOfScreens()
                    ?.filter { it.title == "Kids" && it.screenType == "home" }
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

}