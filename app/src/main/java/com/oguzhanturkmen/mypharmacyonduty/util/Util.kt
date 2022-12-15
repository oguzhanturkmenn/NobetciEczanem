package com.oguzhanturkmen.mypharmacyonduty.util

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun toast(v:Context,msg: String){
    Toast.makeText(v,msg, Toast.LENGTH_LONG).show()
}

fun showSnackbar(v: View, msg: String) {
    Snackbar.make(v, msg, Snackbar.LENGTH_SHORT).show()
}