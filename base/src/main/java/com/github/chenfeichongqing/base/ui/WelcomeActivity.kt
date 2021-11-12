package com.github.chenfeichongqing.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.chenfeichongqing.mvvmlib.util.startActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity<MainActivity>()
        finish()
    }
}