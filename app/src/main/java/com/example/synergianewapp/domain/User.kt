package com.example.synergianewapp.domain

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

class User (): Serializable {
    var login = ""
    var senha = ""

    override fun toString(): String {
        return "User(nome='$login')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}