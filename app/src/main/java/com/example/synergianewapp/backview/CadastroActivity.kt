package com.example.synergianewapp.backview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.synergianewapp.R
import com.example.synergianewapp.domain.Disciplina
import com.example.synergianewapp.domain.DisciplinaService
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        setTitle("Nova Disciplina")


        salvarDisciplina.setOnClickListener {
            val disciplina = Disciplina()
            disciplina.nome = nomeDisciplina.text.toString()
            disciplina.ementa = ementaDisciplina.text.toString()
            disciplina.professor = professorDisciplina.text.toString()
            disciplina.foto = urlFoto.text.toString()
            taskAtualizar(disciplina)
        }
    }

    private fun taskAtualizar(disciplina: Disciplina) {
// Thread para salvar a disciplina
        Thread {
            DisciplinaService.save(disciplina)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }
}