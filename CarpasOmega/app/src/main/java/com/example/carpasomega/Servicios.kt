package com.example.carpasomega

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Servicios : AppCompatActivity() {
    var nombreApellido=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicios)

        val bundle=intent.extras
        nombreApellido=bundle?.getString("nombreApellido").toString()

        val nombreUsuario=findViewById<TextView>(R.id.txtUsuarioLog)
        nombreUsuario.text=nombreApellido
    }
}