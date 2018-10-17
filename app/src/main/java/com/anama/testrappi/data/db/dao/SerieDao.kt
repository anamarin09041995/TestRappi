package com.anama.testrappi.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.anama.testrappi.data.model.Serie
import io.reactivex.Single

@Dao
interface SerieDao {

    @Query("SELECT * FROM serie WHERE category = :category")
    fun lastest(category: Int): Single<List<Serie>>

    @Query("DELETE FROM serie WHERE category = :category")
    fun removeByCategory(category: Int)

    @Insert
    fun insert(series: List<Serie>)
}