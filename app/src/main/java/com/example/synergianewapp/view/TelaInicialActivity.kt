package com.example.synergianewapp.view

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.com.fernandosousa.lmsapp.Prefs
import com.example.synergianewapp.R
import com.example.synergianewapp.adapter.TabsAdapter
import com.example.synergianewapp.domain.Disciplina
import com.example.synergianewapp.fragments.ConfigFragment
import com.example.synergianewapp.fragments.FavoriteFragment
import com.example.synergianewapp.fragments.MainFragment
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.menu_lateral_cabecalho.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val context: Context get() = this
    private var disciplinas = listOf<Disciplina>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)
        //
        setSupportActionBar(toolbar)
        //actionbar
        val actionbar = supportActionBar
        actionbar?.setBackgroundDrawable(getDrawable(R.drawable.background_action_bar))
        //set actionbar title
        actionbar?.title = "Inicio"
        //set back button
        actionbar?.setDisplayHomeAsUpEnabled(false)

        val adapter = TabsAdapter(supportFragmentManager)
        adapter.addFragment(MainFragment(), "Principal")
        adapter.addFragment(ConfigFragment(), "Destaques")
        adapter.addFragment(FavoriteFragment(), "Favoritos")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        // configuração no menu lateral
        configuraMenuLateral()
    }

    private fun configuraMenuLateral() {
        // ícone de menu (hamburger) para mostrar o menu
        var toogle = ActionBarDrawerToggle(
            this,
            layoutMenuLateral,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()
        menu_lateral.setNavigationItemSelectedListener(this)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                // ação enquanto está digitando
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                val progress = ProgressDialog(context)
                progress.setTitle("Synergia App")
                progress.setMessage("Procurando por $query")
                progress.show()
                Toast.makeText(context, "$query", Toast.LENGTH_SHORT).show()
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        if (id == R.id.action_buscar) {
            Toast.makeText(this, "Clicou em buscar", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(this, "Clicou em Atualizar", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.action_config){
            var intent = Intent(this, ConfigActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               when (item.itemId) {
            R.id.nav_home -> {
                Toast.makeText(this, "Clicou Home", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_vagas -> {
                //Navega para atividade de lists and cards da aula do professor
                val intent = Intent(this, VagasActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_vicurriculo -> {
                Toast.makeText(this, "Clicou Mensagens", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_cadcurriculo -> {
                // Navega para atividade de cadastro no menu lateral
                val intent = Intent (this, CadastroVagaActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_sair -> {
                finish()
            }
        }
// fecha menu depois de tratar o evento
        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }
}
