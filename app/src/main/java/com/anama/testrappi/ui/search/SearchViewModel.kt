package com.anama.testrappi.ui.search

import android.arch.lifecycle.ViewModel
import com.anama.testrappi.data.model.Movie
import com.anama.testrappi.data.model.Serie
import com.anama.testrappi.data.net.SearchClient
import com.anama.testrappi.util.applySchedulers
import io.reactivex.Observable

class SearchViewModel (private val client: SearchClient, private val apiKey: String) : ViewModel() {

    fun searchSerie(query: String, page: Int, type: Int): Observable<List<Serie>> =
            client.searchSerie(apiKey, page, "es-ES", query)
                    .map { it.results }
                    .applySchedulers()

    fun searchMovie(query: String, page: Int, type: Int): Observable<List<Movie>> =
            client.searchMovie(apiKey, page, "es-ES", query)
                    .map { it.results }
                    .applySchedulers()

}