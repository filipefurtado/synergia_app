package com.example.synergianewapp.domain

import android.content.Context
import android.util.Log
import br.com.fernandosousa.lmsapp.AndroidUtils
import br.com.fernandosousa.lmsapp.AndroidUtils.parserJson
import br.com.fernandosousa.lmsapp.HttpHelper
import br.com.fernandosousa.lmsapp.Response

object DisciplinaService {

    val host = "https://hrgsilva.pythonanywhere.com"
    val TAG = "Synergia App"

    fun getDisciplinas(context: Context): List<Disciplina> {
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/disciplinas"
            val json = HttpHelper.get(url)
            return parserJson(json)
        } else {
            val dao = DatabaseManager.getVagasDAO()
            return dao.findAll()
        }
    }

    fun getDisciplina(context: Context, id: Long): Disciplina? {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/disciplinas/${id}"
            val json = HttpHelper.get(url)
            val disciplina = parserJson<Disciplina>(json)

            return disciplina
        } else {
            val dao = DatabaseManager.getVagasDAO()
            val disciplina = dao.getById(id)
            return disciplina
        }
    }

    fun save(disciplina: Disciplina, context: Context): Response? {
        if (AndroidUtils.isInternetDisponivel(context)) {
            val json = HttpHelper.post("$host/disciplinas", disciplina.toJson())
            return parserJson<Response>(json)
        } else {
            val dao = DatabaseManager.getVagasDAO()
            dao.insert(disciplina)
            return null
        }
    }

    fun delete(disciplina: Disciplina, context: Context): Response? {
        if (AndroidUtils.isInternetDisponivel(context)) {
            Log.d(TAG, disciplina.id.toString())
            val url = "$host/disciplinas/${disciplina.id}"
            val json = HttpHelper.delete(url)
            Log.d(TAG, json)
            return parserJson(json)
        } else {
            val dao = DatabaseManager.getVagasDAO()
            dao.delete(disciplina)
            return null
        }
    }
}