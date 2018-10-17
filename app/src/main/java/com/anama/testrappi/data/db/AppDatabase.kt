package com.anama.testrappi.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.anama.testrappi.data.db.dao.MovieDao
import com.anama.testrappi.data.db.dao.SerieDao
import com.anama.testrappi.data.model.Movie
import com.anama.testrappi.data.model.Serie

@Database(entities = arrayOf(Movie::class, Serie::class), version = 4)

abstract class AppDatabase: RoomDatabase(){
    abstract fun movieDao(): MovieDao //Proveo el acceso a mi Dao
    abstract fun serieDao(): SerieDao

}