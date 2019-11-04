package br.com.fernandosousa.lmsapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AndroidUtils {
    // verificar se existe algum tipo de conexão disponível
    fun isInternetDisponivel(context: Context): Boolean {
        val conexao = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val redes = conexao.allNetworks
        return redes.map { conexao.getNetworkInfo(it) }
            .any { it.state == NetworkInfo.State.CONNECTED }
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>() {}.type
        return Gson().fromJson<T>(json, type)
    }
}