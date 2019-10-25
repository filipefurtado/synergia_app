package com.example.synergianewapp.backview

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
import com.example.synergianewapp.R
import com.example.synergianewapp.domain.Disciplina
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var disciplinas = listOf<Disciplina>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)
        // cards comentados, só descomentar que funciona
//        recyclerDisciplinas?.layoutManager = LinearLayoutManager(context)
//        recyclerDisciplinas?.itemAnimator = DefaultItemAnimator()
//        recyclerDisciplinas?.setHasFixedSize(true)

// meuuuuuu deusussssss obrigado!!!
        setSupportActionBar(toolbar)
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar?.title = "Inicio"
        //set back button
        actionbar?.setDisplayHomeAsUpEnabled(false)

        // configuração no menu lateral
        configuraMenuLateral()
    }

    //==============================================================================================

    // lista de cards ; deixei comentado pois passei para outra activity
//    override fun onResume() {
//        super.onResume()
//// task para recuperar as disciplinas
//        taskDisciplinas()
//    }
//
//    fun taskDisciplinas() {
//        disciplinas = DisciplinaService.getDisciplinas(context)
//// atualizar lista
//        recyclerDisciplinas?.adapter = DisciplinaAdapter(disciplinas) { onClickDisciplina(it) }
//    }
//
//    // tratamento do evento de clicar em uma disciplina
//// tratamento do evento de clicar em uma disciplina
//    fun onClickDisciplina(disciplina: Disciplina) {
//        Toast.makeText(context, "Clicou disciplina ${disciplina.nome}", Toast.LENGTH_SHORT).show()
//        val intent = Intent(context, DisciplinaActivity::class.java)
//        intent.putExtra("disciplina", disciplina)
//        startActivity(intent)
//    }
    //==============================================================================================

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
                val intent = Intent (this, CadastroActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_alcurriculo -> {
                Toast.makeText(this, "Clicou Localização", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_config -> {
                Toast.makeText(this, "Clicou Config", Toast.LENGTH_SHORT).show()
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
