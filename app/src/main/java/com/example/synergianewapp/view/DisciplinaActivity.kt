package com.example.synergianewapp.view

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.synergianewapp.R
import com.example.synergianewapp.domain.Disciplina
import com.example.synergianewapp.domain.DisciplinaService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_vaga_contents.*
import kotlinx.android.synthetic.main.activity_vaga.*
import kotlinx.android.synthetic.main.activity_vaga.toolbar

class DisciplinaActivity : AppCompatActivity() {
    private val context: Context get() = this
    var disciplina: Disciplina? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaga)

        setListeners()

        setSupportActionBar(toolbar)
        // recuperar objeto de Disciplina da Intent
        disciplina = intent.getSerializableExtra("disciplina") as Disciplina

        // alterar título da ActionBar
        supportActionBar?.title = disciplina?.nome

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        // atualizar dados da disciplina
        nomeDisciplina.text = disciplina?.nome
        Picasso.with(this).load(disciplina?.foto).fit().into(imagemDisciplina,
            object : com.squareup.picasso.Callback {
                override fun onSuccess() {}

                override fun onError() {}
            })

        ementaDisciplina.text = disciplina?.ementa
        Picasso.with(this).load(disciplina?.foto).fit().into(imagemDisciplina,
            object : com.squareup.picasso.Callback {
                override fun onSuccess() {}

                override fun onError() {}
            })
// Pega o conteúdo da api professor e coloca é uma variável
        professorDisciplina.text = disciplina?.professor
        Picasso.with(this).load(disciplina?.foto).fit().into(imagemDisciplina,
            object : com.squareup.picasso.Callback {
                override fun onSuccess() {}

                override fun onError() {}
            })
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.main_menu_disciplina, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // id do item clicado
        val id = item?.itemId
        // verificar qual item foi clicado
        // remover a disciplina no WS
        if (id == R.id.action_remover) {
            // alerta para confirmar a remeção
            // só remove se houver confirmação positiva
            AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("Deseja excluir a disciplina")
                .setPositiveButton("Sim") { dialog, _ ->
                    dialog.dismiss()
                    taskExcluir()
                }.setNegativeButton("Não") { dialog, _ ->
                    dialog.dismiss()
                }.create().show()
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun taskExcluir() {
        if (this.disciplina != null && this.disciplina is Disciplina) {
            // Thread para remover a disciplina
            Thread {
                DisciplinaService.delete(this.disciplina as Disciplina, this)
                runOnUiThread {
                    // após remover, voltar para activity anterior
                    finish()
                }
            }.start()
        }
    }

    private fun setListeners() {
        fab.setOnClickListener {
            Toast.makeText(this, "Adicionado ao favaritos", Toast.LENGTH_SHORT).show()
        }
    }
}
