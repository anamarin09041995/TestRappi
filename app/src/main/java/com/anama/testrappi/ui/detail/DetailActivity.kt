package com.anama.testrappi.ui.detail

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.transition.TransitionManager
import android.view.MenuItem
import com.anama.testrappi.R
import com.anama.testrappi.R.id.toolbar
import com.anama.testrappi.data.Global
import com.anama.testrappi.data.model.Item
import com.anama.testrappi.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.contentView
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    val item: Item = Item()
    val global: Global by inject ()
    val noDescriptionSet: ConstraintSet = ConstraintSet()
    val fullSet:ConstraintSet = ConstraintSet()
    var full:Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.item = global.selected


        noDescriptionSet.clone(this, R.layout.content_detail)
        fullSet.clone(root)

        fab.setOnClickListener{
            TransitionManager.beginDelayedTransition(root)
            if(full) noDescriptionSet.applyTo(root)
            else fullSet.applyTo(root)
            full = !full
        }


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
    }
}
