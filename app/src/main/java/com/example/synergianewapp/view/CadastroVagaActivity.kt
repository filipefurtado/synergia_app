package com.example.synergianewapp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.synergianewapp.R
import com.example.synergianewapp.domain.Disciplina
import com.example.synergianewapp.domain.DisciplinaService
import kotlinx.android.synthetic.main.activity_vaga_cadastro.*

class CadastroVagaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaga_cadastro)


        val actionbar = supportActionBar
        //set actionbar title
        actionbar?.title = "Cadastro de Vagas"
        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        salvarDisciplina.setOnClickListener { saveApi() }
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

    private fun taskAtualizar(disciplina: Disciplina) {
        // Thread para salvar a disciplina
        Thread {
            DisciplinaService.save(disciplina,this)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }

    private fun saveApi() {
        val disciplina = Disciplina()
        if (nomeDisciplina.text.toString() == "" || ementaDisciplina.text.toString() == "" || professorDisciplina.text.toString() == "" ||
            urlFoto.text.toString() == ""
        ) {
            Toast.makeText(this, "Todos Os Campos Devem Ser Preenchidos!", Toast.LENGTH_LONG).show()
        } else {
            disciplina.nome = nomeDisciplina.text.toString()
            disciplina.ementa = ementaDisciplina.text.toString()
            disciplina.professor = professorDisciplina.text.toString()
            disciplina.foto = urlFoto.text.toString()
            taskAtualizar(disciplina)
        }
    }
}

