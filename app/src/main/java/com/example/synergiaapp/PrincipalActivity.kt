package com.example.synergiaapp

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast

class PrincipalActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Funcionará em breve, Synergia Sys", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.principal, menu)
        // Linha abaixo vincula o evento de busca
        (menu.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        // Linha abaixo recebe os caravteres digitados
            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        // Linha abaixa pega os caracteres recebidos
            override fun onQueryTextSubmit(query: String?): Boolean {
            Toast.makeText(contexto, query,
                Toast.LENGTH_LONG).show()
                return false
            }
        })
        return true
    }


    // Criada uma variavel contexto com o valor this, é uma referencia a sua classe pai, uma referencia a classe atual
    // Essa varial foi criado por que se fizermos uma referencia a super classe usando "this" dentro de "onQueryTextSubmit"
    // ele vai referenciar a super classe do "onCreateOptionsMenu" e não do "PrincipalActivity"
    val contexto = this

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        if (id == R.id.action_buscar){
            Toast.makeText(this, "clicou em buscar",
                Toast.LENGTH_LONG).show()
        }
        else if (id == R.id.action_atualizar){
            Toast.makeText(this,"clicou em atualizar",
                Toast.LENGTH_LONG).show()
        }
        else if(id == R.id.action_add){
            Toast.makeText(this,"clicou em adicionar",
                Toast.LENGTH_LONG).show()
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
        else if (id == R.id.action_sair){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}