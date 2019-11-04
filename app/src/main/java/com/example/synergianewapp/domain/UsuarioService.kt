package com.example.synergianewapp.domain

import android.content.Context
import br.com.fernandosousa.lmsapp.AndroidUtils
import br.com.fernandosousa.lmsapp.AndroidUtils.parserJson
import br.com.fernandosousa.lmsapp.HttpHelper

object UsuarioService {

    val host = "https://hrgsilva.pythonanywhere.com"
    val TAG = "Synergia App"

    fun login(context: Context, user: User): Boolean{
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/login"
            val json = HttpHelper.post(url,user.toJson())
            return parserJson(json)
        } else {
            return false
        }
    }
}