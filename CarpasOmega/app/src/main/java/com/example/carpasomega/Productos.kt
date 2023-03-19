package com.example.carpasomega

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class Productos : AppCompatActivity() {
    var nombreApellido=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        val bundle=intent.extras
        nombreApellido=bundle?.getString("nombreApellido").toString()

        val nombreUsuario=findViewById<TextView>(R.id.txtUsuarioLog)
        nombreUsuario.text=nombreApellido
         val opcVehiculos=findViewById<ImageView>(R.id.opcVehiculos)
            opcVehiculos.setOnClickListener {

                Toast.makeText(this, "Producto agregado al carrito", Toast.LENGTH_SHORT).show()
            }

        val btnCarrito=findViewById<ImageView>(R.id.imgCarrito)
        btnCarrito.setOnClickListener {
            val intenProductos= Intent(this,Venta::class.java)
            intenProductos.putExtra("nombreApellido",nombreUsuario.text.toString())
            startActivity(intenProductos)
        }


    }
}