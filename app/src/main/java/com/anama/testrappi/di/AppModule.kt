package com.anama.testrappi.di

import android.arch.persistence.room.Room
import com.anama.testrappi.R
import com.anama.testrappi.data.Global
import com.anama.testrappi.data.db.AppDatabase
import com.anama.testrappi.data.net.MovieClient
import com.anama.testrappi.data.net.SearchClient
import com.anama.testrappi.data.net.SerieClient
import com.anama.testrappi.ui.main.movie.MovieViewModel
import com.anama.testrappi.ui.main.serie.SerieViewModel
import com.anama.testrappi.ui.search.SearchViewModel
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.experimental.builder.viewModel
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
    single<SearchClient>{ get<Retrofit>().create(SearchClient::class.java) }
    single<SerieClient>{ get<Retrofit>().create(SerieClient::class.java) }
    single<MovieClient>{ get<Retrofit>().create(MovieClient::class.java) }

    single {
        Room
                .databaseBuilder(androidApplication(), AppDatabase::class.java, "item.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    single{ get<AppDatabase>().movieDao()}
    single{ get<AppDatabase>().serieDao()}
    single{ Global()}

    single {
        androidApplication().getString(R.string.apiKey)
    }

    viewModel<MovieViewModel>()
    viewModel<SerieViewModel>()
    viewModel<SearchViewModel>()

}