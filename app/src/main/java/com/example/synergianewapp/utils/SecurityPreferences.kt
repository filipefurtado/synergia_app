package com.example.synergianewapp.utils

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context){
    private val SharedPreferences: SharedPreferences = context.getSharedPreferences("Synergia App", Context.MODE_PRIVATE)

    fun storeString(key:String, value: String){
        SharedPreferences.edit().putString(key,value).apply()
    }

    fun getSttorageString(key: String): String? {
        return SharedPreferences.getString(key, "")
    }
}