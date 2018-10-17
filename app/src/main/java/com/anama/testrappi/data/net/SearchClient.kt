package com.anama.testrappi.data.net

import com.anama.testrappi.data.model.Movie
import com.anama.testrappi.data.model.Serie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchClient{

    @GET("/3/search/movie")
    fun searchMovie(@Query("api_key") apiKey: String, @Query("page") page:Int, @Query("language") language:String, @Query("query") query:String): Observable<Response<Movie>>

    @GET("/3/search/tv")
    fun searchSerie(@Query("api_key") apiKey: String, @Query("page") page:Int, @Query("language") language:String, @Query("query") query:String): Observable<Response<Serie>>
}