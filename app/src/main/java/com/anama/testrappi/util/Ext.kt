package com.anama.testrappi.util

import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup

fun AppCompatActivity.putFragment(container: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .commit()
}

fun ViewGroup.inflate(@LayoutRes layout:Int) = LayoutInflater.from(context).inflate(layout, this, false)