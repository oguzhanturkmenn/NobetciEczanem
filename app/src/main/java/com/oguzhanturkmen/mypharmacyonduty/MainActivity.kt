package com.oguzhanturkmen.mypharmacyonduty

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.oguzhanturkmen.mypharmacyonduty.fragments.PharmacyDetailsFragmentDirections
import com.oguzhanturkmen.mypharmacyonduty.fragments.PharmacyFragmentDirections
import kotlinx.android.synthetic.main.my_toolbar.*

class MainActivity : AppCompatActivity() {

    private lateinit var myToolbar: androidx.appcompat.widget.Toolbar
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            val actionBar: ActionBar? = supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(false)
        }

    }

}
