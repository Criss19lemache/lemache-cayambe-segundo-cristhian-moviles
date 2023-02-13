package com.example.deber01_bodegaproducto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class UpdateBodega : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_bodega)

        val bundle=intent.extras

        val nombre = findViewById<EditText>(R.id.txtnombre)
        val totalProductos = findViewById<EditText>(R.id.txtMarca)
        val telefono = findViewById<EditText>(R.id.txtPrecio)
        val gerente = findViewById<EditText>(R.id.txtStock)

        val id=bundle?.getString("id")

        nombre.setText(bundle?.getString("nombre"))
        totalProductos.setText(bundle?.getString("totalProductos"))
        telefono.setText(bundle?.getString("telefono"))
        gerente.setText(bundle?.getString("gerente"))

        val btnEditarBodega=findViewById<Button>(R.id.btn_ingresarProducto)
        btnEditarBodega.setOnClickListener {
            BaseDeDatos.tablaBodega!!.editarBodega(
                id.toString().toInt(),
                nombre.text.toString(),
                totalProductos.text.toString().toInt(),
                telefono.text.toString(),
                gerente.text.toString())
            Toast.makeText(this, "Se actualizó la bodega correctamente ", Toast.LENGTH_SHORT).show()
        }


    }

}