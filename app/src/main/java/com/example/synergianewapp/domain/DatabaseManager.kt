package com.example.synergianewapp.domain

import android.arch.persistence.room.Room
import com.example.synergianewapp.LMSApplication

object DatabaseManager {

    private var dbInstance: SynergiaDatabase

    init {
        val appContext =
            LMSApplication.getInstance().applicationContext

        dbInstance = Room.databaseBuilder(
            appContext,
            SynergiaDatabase::class.java,
            "synergia.sqlite"
        ).build()
    }
    fun getVagasDAO(): VagasDAO {
        return dbInstance.vagasDao()
    }
}