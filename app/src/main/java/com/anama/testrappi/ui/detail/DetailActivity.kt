package com.anama.testrappi.ui.detail

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.anama.testrappi.R
import com.anama.testrappi.data.Global
import com.anama.testrappi.data.model.Item
import com.anama.testrappi.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    val item: Item = Item()
    val global: Global by inject ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.item = global.selected

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
