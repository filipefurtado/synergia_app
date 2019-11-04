package com.example.synergianewapp.domain

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Disciplina::class), version = 1)
abstract class SynergiaDatabase: RoomDatabase() {

    abstract fun vagasDao(): VagasDAO
}