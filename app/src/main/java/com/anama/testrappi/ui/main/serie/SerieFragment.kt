package com.anama.testrappi.ui.main.serie


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.transition.Explode
import android.transition.Slide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.anama.testrappi.R
import com.anama.testrappi.data.Global
import com.anama.testrappi.data.model.Item
import com.anama.testrappi.data.model.Serie
import com.anama.testrappi.ui.adapter.ItemAdapter
import com.anama.testrappi.ui.detail.DetailActivity
import com.anama.testrappi.util.LifeDisposable
import com.anama.testrappi.util.subscribeByShot
import com.facebook.drawee.view.SimpleDraweeView
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_serie.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class SerieFragment : Fragment(){

    val dis: LifeDisposable = LifeDisposable(this)
    val category: Int by lazy { arguments!!.getInt(EXTRA_CATEGORY) }
    val adapter: ItemAdapter<Serie> = ItemAdapter()
    val viewModel: SerieViewModel by viewModel()
    val global: Global by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_serie, container, false)
    }

    override fun onResume() {
        super.onResume()

        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(activity)

        dis add adapter.scrollEnd
                .map { 1 }
                .scan(1, {a, v -> a + v})
                .flatMap { viewModel.getSeriesByPage(category, it) }
                .subscribe { adapter.addItems(it) }

        dis add viewModel.getFirstPage(category).subscribeByShot(
                onNext = {adapter.items = it.toMutableList() },
                onHttpError = { toast(it) },
                onError = { toast(it.message!!) })

        dis add adapter.clickItem
                .subscribeBy(onNext = { navigateToDetail(it) })

    }

    fun navigateToDetail(item: Item){
        global.selected = item
        startActivity<DetailActivity>()

    }

    companion object {
        val EXTRA_CATEGORY = "category"
        fun instance(itemCategory: Int): SerieFragment {
            val fragment = SerieFragment()
            val args = Bundle()
            args.putInt(EXTRA_CATEGORY, itemCategory)
            fragment.arguments = args
            return fragment
        }
    }

}