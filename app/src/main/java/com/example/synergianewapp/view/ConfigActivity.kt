package com.example.synergianewapp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.synergianewapp.R
import kotlinx.android.synthetic.main.activity_config.*

class ConfigActivity : AppCompatActivity() {
    val contexto =  this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        //Infla a actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar?.title = "Configurações"
        //set back button
        actionbar?.setDisplayHomeAsUpEnabled(true)

        config_account.setOnClickListener{ configAccount() }
        config_notification.setOnClickListener{ configNotification() }
        config_export.setOnClickListener{ configExport() }
        config_help.setOnClickListener{ configHelp() }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configAccount(){
        Toast.makeText(contexto,"Clicou em Conta", Toast.LENGTH_SHORT).show()
    }

    private fun configNotification(){
        Toast.makeText(contexto,"Clicou em Notificação", Toast.LENGTH_SHORT).show()
    }

    private fun configExport(){
        Toast.makeText(contexto,"Clicou em Exportar", Toast.LENGTH_SHORT).show()
    }
    private fun configHelp(){
        Toast.makeText(contexto,"Clicou em Ajuda", Toast.LENGTH_SHORT).show()
    }
}
