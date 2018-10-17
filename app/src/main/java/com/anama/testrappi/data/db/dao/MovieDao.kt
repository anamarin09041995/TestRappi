package com.anama.testrappi.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.anama.testrappi.data.model.Movie
import io.reactivex.Single

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie WHERE category = :category")
    fun lastest(category: Int): Single<List<Movie>>

    @Query("DELETE FROM movie WHERE category = :category")
    fun removeByCategory(category: Int)

    @Insert
    fun insert(movies: List<Movie>)
}