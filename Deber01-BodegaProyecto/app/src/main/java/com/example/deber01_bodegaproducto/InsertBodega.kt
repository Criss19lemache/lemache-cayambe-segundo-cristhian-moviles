package com.example.deber01_bodegaproducto

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class InsertBodega : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_bodega)

        val nombre = findViewById<EditText>(R.id.txtnombre)
        val totalProductos = findViewById<EditText>(R.id.txtMarca)
        val telefono = findViewById<EditText>(R.id.txtPrecio)
        val gerente = findViewById<EditText>(R.id.txtStock)
        val btnCrear=findViewById<Button>(R.id.btn_ingresarProducto)
            btnCrear
            .setOnClickListener {

                BaseDeDatos.tablaBodega!!.ingresarBodega(

                    nombre.text.toString(),
                    totalProductos.text.toString().toInt(),
                    telefono.text.toString(),
                    gerente.text.toString()
                )
                Toast.makeText(this, "Se ingresó una bodega correctamente ", Toast.LENGTH_SHORT).show()
                //Limpiar Texto
                val nombre = findViewById<EditText>(R.id.txtnombre)
                val totalProductos = findViewById<EditText>(R.id.txtMarca)
                val telefono = findViewById<EditText>(R.id.txtPrecio)
                val gerente = findViewById<EditText>(R.id.txtStock)
                nombre.setText("")
                totalProductos.setText("")
                telefono.setText("")
                gerente.setText("")
            }


    }

}