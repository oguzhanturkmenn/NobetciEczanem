package com.ak.nobetcieczane.util

import android.content.Context

fun getJsonDataFromAsset(context:Context,fileName:String):String?{
    val jsonString:String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    }catch (e:Exception){
        e.printStackTrace()
      return null
    }
    return jsonString
}