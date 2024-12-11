package com.example.praktikumweek9.repository

import com.example.praktikumweek9.data.entity.Mahasiswa

interface RepositoryMhs {
    suspend fun insertMhs(mahasiswa: Mahasiswa)
}