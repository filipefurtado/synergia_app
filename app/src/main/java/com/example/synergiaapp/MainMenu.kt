package com.example.synergiaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item?.itemId

        if (id == R.id.action_buscar) {

            Toast.makeText(this, "Buscar button", Toast.LENGTH_SHORT).show()

        } else if (id == R.id.action_adicionar) {

            addClick()

        } else if (id == R.id.action_atualizar) {

            Toast.makeText(this, "Atualizar button", Toast.LENGTH_SHORT).show()

        } else if (id == R.id.action_configuracoes) {

            configClick()

        } else if (id == R.id.action_sair) {

            exitClick()

        }

        return super.onOptionsItemSelected(item)
    }

    fun addClick() {
        var addIntent = Intent(this, AddMenu::class.java)
        startActivity(addIntent)
    }

    fun configClick() {
        var configIntent = Intent(this, ConfigMenu::class.java)
        startActivity(configIntent)
    }

    fun exitClick() {
        var returnIntent = Intent(this, MainActivity::class.java)
        startActivity(returnIntent)
    }
}
