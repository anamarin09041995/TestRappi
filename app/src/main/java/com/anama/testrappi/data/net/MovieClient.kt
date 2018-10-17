package com.anama.testrappi.data.net

import com.anama.testrappi.data.model.Movie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieClient{

    @GET("/3/movie/popular")
    fun getPopular(@Query("api_key") apiKey: String, @Query("page") page:Int, @Query("language") language:String): Observable<Response<Movie>>

    @GET("/3/movie/top_rated")
    fun getTopRated(@Query ("api_key") apiKey: String, @Query("page") page:Int, @Query("language") language:String): Observable<Response<Movie>>

    @GET("/3/movie/upcoming")
    fun getUpcoming(@Query ("api_key") apiKey: String, @Query("page") page:Int, @Query("language") language:String): Observable<Response<Movie>>

}