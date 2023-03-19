package com.example.carpasomega

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Menu : AppCompatActivity() {

    var nombreApellido=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val bundle=intent.extras
        nombreApellido=bundle?.getString("nombreApellido").toString()

        val nombreUsuario=findViewById<TextView>(R.id.txtUsuarioLog)
        nombreUsuario.text=nombreApellido

        val btnProductos=findViewById<Button>(R.id.btnProductos)
        btnProductos.setOnClickListener {
            val intenProductos= Intent(this,Productos::class.java)
            intenProductos.putExtra("nombreApellido",nombreUsuario.text.toString())
            startActivity(intenProductos)
        }

        val btnServicios=findViewById<Button>(R.id.btnServicios)
        btnServicios.setOnClickListener {
            val intenProductos= Intent(this,Servicios::class.java)
            intenProductos.putExtra("nombreApellido",nombreUsuario.text.toString())
            startActivity(intenProductos)
        }

        val btnPromociones=findViewById<Button>(R.id.btnPromociones)
        btnPromociones.setOnClickListener {
            val intenProductos= Intent(this,Promociones::class.java)
            intenProductos.putExtra("nombreApellido",nombreUsuario.text.toString())
            startActivity(intenProductos)
        }

        val btnGaleria=findViewById<Button>(R.id.btnGaleria)
        btnGaleria.setOnClickListener {
            val intenProductos= Intent(this,Galeria::class.java)
            intenProductos.putExtra("nombreApellido",nombreUsuario.text.toString())
            startActivity(intenProductos)
        }

        val btnContacto=findViewById<Button>(R.id.btnContacto)
        btnContacto.setOnClickListener {
            val intenProductos= Intent(this,Contacto::class.java)
            intenProductos.putExtra("nombreApellido",nombreUsuario.text.toString())
            startActivity(intenProductos)
        }

        val btnCarrito=findViewById<ImageView>(R.id.imgCarrito)
        btnCarrito.setOnClickListener {
            val intenProductos= Intent(this,Venta::class.java)
            intenProductos.putExtra("nombreApellido",nombreUsuario.text.toString())
            startActivity(intenProductos)
        }




    }
}