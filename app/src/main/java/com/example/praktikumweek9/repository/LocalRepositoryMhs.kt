package com.example.praktikumweek9.repository

import com.example.praktikumweek9.data.dao.MahasiswaDao
import com.example.praktikumweek9.data.entity.Mahasiswa

class LocalRepositoryMhs(
    private val mahasiswaDao: MahasiswaDao
) : RepositoryMhs {
    override suspend fun insertMhs(mahasiswa: Mahasiswa) {
        mahasiswaDao.insertMahasiswa(mahasiswa)
    }
}