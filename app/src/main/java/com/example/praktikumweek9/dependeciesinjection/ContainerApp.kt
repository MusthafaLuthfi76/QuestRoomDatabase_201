package com.example.praktikumweek9.dependeciesinjection

import android.content.Context
import com.example.praktikumweek9.data.database.KrsDatabase
import com.example.praktikumweek9.repository.LocalRepositoryMhs
import com.example.praktikumweek9.repository.RepositoryMhs

interface InterfaceContainerApp {
    val repositoryMhs: RepositoryMhs
}

class ContainerApp(private val context: Context): InterfaceContainerApp{
    override val repositoryMhs: RepositoryMhs by lazy {
        LocalRepositoryMhs(KrsDatabase.getDatabase(context).mahasiswaDao())
    }
}