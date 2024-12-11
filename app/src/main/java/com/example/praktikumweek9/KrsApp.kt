package com.example.praktikumweek9

import android.app.Application
import com.example.praktikumweek9.dependeciesinjection.ContainerApp

class KrsApp : Application() {
    lateinit var containerApp: ContainerApp // Fungsinya untuk menyimpan

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this) //Membuat Instance
        // Instance adalah object yang dibuat dari class
    }
}