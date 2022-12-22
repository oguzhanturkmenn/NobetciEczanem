package com.oguzhanturkmen.mypharmacyonduty

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.oguzhanturkmen.mypharmacyonduty.fragments.PharmacyDetailsFragmentDirections
import com.oguzhanturkmen.mypharmacyonduty.fragments.PharmacyFragmentDirections
import kotlinx.android.synthetic.main.my_toolbar.*

class MainActivity : AppCompatActivity() {

    private lateinit var myToolbar: androidx.appcompat.widget.Toolbar
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myToolbar = findViewById(R.id.toolbarr)
        setSupportActionBar(toolbarr)
        if (supportActionBar != null) {
            val actionBar: ActionBar? = supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(false)
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        navHostFragment.navController.addOnDestinationChangedListener{_ ,destination,_ ->

         when(destination.id){
             R.id.aboutAppFragment -> {
                 myToolbar.visibility = View.GONE
             }
             else -> {
                 myToolbar.visibility = View.VISIBLE
             }
         }

        }

    }



}
