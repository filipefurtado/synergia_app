package com.example.synergiaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.setOnClickListener {
            onClickButton()
        }
    }

    fun onClickButton(){
        val userName = userText.text.toString()
        val userPassword = passwordText.text.toString()

        if (userName == "aluno" && userPassword == "impacta") {
            var intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Usuário ou senha incorretos!", Toast.LENGTH_SHORT).show()
        }
    }
}
