package com.anama.testrappi.ui.main.movie

import android.arch.lifecycle.ViewModel
import com.anama.testrappi.data.db.dao.MovieDao
import com.anama.testrappi.data.model.Item
import com.anama.testrappi.data.model.Movie
import com.anama.testrappi.data.net.MovieClient
import com.anama.testrappi.util.applySchedulers
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class MovieViewModel (private val client: MovieClient, private val dao: MovieDao, private val apiKey: String) : ViewModel() {

    fun getFirstPage(category: Int): Observable<List<Movie>> = dao.lastest(category).toObservable()
            .concatWith(Observable.timer(200, TimeUnit.MILLISECONDS)
                    .flatMap { getMovies(category, 1) })
            .applySchedulers()

    fun getMoviesByPage(category: Int, page: Int) = getMovies(category, page)
            .applySchedulers()

    private fun getMovies(category: Int, page: Int) = when (category) {
        Item.CATEGORY_POPULAR -> client.getPopular(apiKey, page, "es-ES")
        Item.CATEGORY_UPCOMING -> client.getUpcoming(apiKey, page, "es-ES")
        else -> client.getTopRated(apiKey, page, "es-ES")
    }
            .map {
                if (page == 1) dao.removeByCategory(category)
                dao.insert(it.results.map {
                    it.category = category
                    it
                })
                it.results
            }


}