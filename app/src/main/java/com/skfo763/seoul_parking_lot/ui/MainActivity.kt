package com.skfo763.seoul_parking_lot.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skfo763.seoul_parking_lot.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textview.text = "hello~~"
    }
}
