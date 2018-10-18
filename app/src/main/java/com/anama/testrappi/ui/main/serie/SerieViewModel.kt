package com.anama.testrappi.ui.main.serie

import android.arch.lifecycle.ViewModel
import com.anama.testrappi.data.db.dao.SerieDao
import com.anama.testrappi.data.model.Item
import com.anama.testrappi.data.model.Serie
import com.anama.testrappi.data.net.SerieClient
import com.anama.testrappi.util.applySchedulers
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class SerieViewModel (private val client: SerieClient, private val dao: SerieDao, private val apiKey: String) : ViewModel() {

    fun getFirstPage(category: Int): Observable<List<Serie>> = dao.lastest(category).toObservable()
            .concatWith(Observable.timer(200, TimeUnit.MILLISECONDS)
                    .flatMap{ getSeries(category, 1) })
            .applySchedulers()

    fun getSeriesByPage(category: Int, page: Int) = getSeries(category, page)
            .applySchedulers()

    private fun getSeries(category: Int, page: Int) = when (category) {
        Item.CATEGORY_POPULAR -> client.getPopular(apiKey, page, "es-ES")
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