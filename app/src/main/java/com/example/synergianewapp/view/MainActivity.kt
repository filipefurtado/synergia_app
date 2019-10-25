package com.example.synergianewapp.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.synergianewapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonStart.setOnClickListener{
            val Slogin: String = inputUser.text.toString()
            val Spassword:String = inputPassword.text.toString()

            if (Slogin == "aluno" && Spassword == "impacta"){
//                Toast.makeText(this, "Bem vindo, $Slogin!", Toast.LENGTH_SHORT).show()
                val intent: Intent = Intent(this, TelaInicialActivity::class.java)
                startActivity(intent)
            }
            else if(Slogin == "" && Spassword == ""){
                Toast.makeText(
                    this, "Campos em branco, Informe usuário e senha!",
                    Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(
                    this, "Usuário ou senha incorretos",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}
