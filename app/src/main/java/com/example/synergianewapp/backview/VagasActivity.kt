package com.example.synergianewapp.backview

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.synergianewapp.R
import com.example.synergianewapp.adapter.DisciplinaAdapter
import com.example.synergianewapp.domain.Disciplina
import com.example.synergianewapp.domain.DisciplinaService
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class VagasActivity : AppCompatActivity() {

    private val context: Context get() = this
    private var disciplinas = listOf<Disciplina>()
    private var REQUEST_CADASTRO = 1
    private var REQUEST_REMOVE = 2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)
        recyclerDisciplinas?.layoutManager = LinearLayoutManager(context)
        recyclerDisciplinas?.itemAnimator = DefaultItemAnimator()
        recyclerDisciplinas?.setHasFixedSize(true)

        setSupportActionBar(toolbar)
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar?.title = "Oportunidades Synergia"
        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        // infla o menu com os botões da ActionBar
//        menuInflater.inflate(R.menu.main_menu_disciplina, menu)
//        return true
//    }

    override fun onResume() {
        super.onResume()
// task para recuperar as disciplinas
        taskDisciplinas()
    }

    fun taskDisciplinas() {
        Thread {
            // Código para procurar as disciplinas
            // que será executado em segundo plano / Thread separada
            this.disciplinas = DisciplinaService.getDisciplinas(context)
            runOnUiThread {
                // Código para atualizar a UI com a lista de disciplinas
                recyclerDisciplinas?.adapter =
                    DisciplinaAdapter(this.disciplinas) { onClickDisciplina(it) }
            }
        }.start()
    }

    // tratamento do evento de clicar em uma disciplina
// tratamento do evento de clicar em uma disciplina
    fun onClickDisciplina(disciplina: Disciplina) {
        Toast.makeText(context, "Clicou disciplina ${disciplina.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, DisciplinaActivity::class.java)
        intent.putExtra("disciplina", disciplina)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // id do item clicado
        val id = item?.itemId
        // botão up navigation
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


    // esperar o retorno do cadastro da disciplina
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CADASTRO || requestCode == REQUEST_REMOVE) {
            // atualizar lista de disciplinas
            taskDisciplinas()
        }
    }
}
