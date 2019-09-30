package com.example.synergiaapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_config_menu.*

class ConfigMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_menu)

        backConfigMenu.setOnClickListener{
            onClickBackButton()
        }
    }

    fun onClickBackButton(){
        var intent = Intent(this, MainMenu::class.java)
        startActivity(intent)
    }

}