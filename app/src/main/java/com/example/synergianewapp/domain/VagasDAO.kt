package com.example.synergianewapp.domain

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface VagasDAO {

    @Query("SELECT * from disciplina where id = :id")
    fun getById(id: Long): Disciplina?

    @Query("SELECT * from disciplina")
    fun findAll(): List<Disciplina>

    @Insert
    fun insert (disciplina: Disciplina)

    @Delete
    fun delete (disciplina: Disciplina)
}