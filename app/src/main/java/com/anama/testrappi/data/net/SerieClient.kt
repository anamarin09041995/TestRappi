package com.anama.testrappi.data.net

import com.anama.testrappi.data.model.Serie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SerieClient{

    @GET("/3/tv/popular")
    fun getPopular(@Query("api_key") apiKey: String, @Query("page") page:Int, @Query ("language") language:String): Observable<Response<Serie>>

    @GET("/3/tv/top_rated")
    fun getTopRated(@Query("api_key") apiKey: String, @Query("page") page:Int, @Query ("language") language:String): Observable<Response<Serie>>


}