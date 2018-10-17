package com.anama.testrappi.di

import android.arch.persistence.room.Room
import com.anama.testrappi.R
import com.anama.testrappi.data.Global
import com.anama.testrappi.data.db.AppDatabase
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module{

    single{
        Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .baseUrl(androidApplication().getString(R.string.base_url))
                .build()
    }

    single {
        Room
                .databaseBuilder(androidApplication(), AppDatabase::class.java, "item.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    single{ get<AppDatabase>().movieDao()}
    single{ get<AppDatabase>().serieDao()}
    single{ Global()}

}