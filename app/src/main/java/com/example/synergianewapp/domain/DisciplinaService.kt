package com.example.synergianewapp.domain

import android.content.Context
import android.util.Log
import br.com.fernandosousa.lmsapp.AndroidUtils
import br.com.fernandosousa.lmsapp.HttpHelper
import br.com.fernandosousa.lmsapp.Response
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.net.URL

object DisciplinaService {

    val host = "https://hrgsilva.pythonanywhere.com"
    val TAG = "Synergia App"

    fun getDisciplinas(context: Context): List<Disciplina> {
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/disciplinas"
            val json = HttpHelper.get(url)
            return parserJson(json)
        } else {
            return ArrayList<Disciplina>()
        }
    }

    //==============================================================================================
    fun save(disciplina: Disciplina): Response {
        val json = HttpHelper.post("$host/disciplinas", disciplina.toJson())
        return parserJson<Response>(json)
    }

//    ==============================================================================================
    fun delete(disciplina: Disciplina): Response {
        Log.d(TAG, disciplina.id.toString())
        val url = "$host/disciplinas/${disciplina.id}"
        val json = HttpHelper.delete(url)
        Log.d(TAG, json)
        return parserJson(json)
    }
//    ==============================================================================================

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>() {}.type
        return Gson().fromJson<T>(json, type)
    }



}